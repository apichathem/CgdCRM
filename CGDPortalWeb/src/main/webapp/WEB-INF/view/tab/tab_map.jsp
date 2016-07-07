<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<c:forEach items="${CODEBOOK_LIST.GOOGLE_MAP}" var="googleMap">
	<c:choose>
		<c:when test="${googleMap.codeId == 'MAP_ENABLED' }">	
			<c:set var="MAP_ENABLED" value="${googleMap.etc3 }"  />
		</c:when>
		<c:when test="${googleMap.codeId == 'API_KEY' }">	
			<c:set var="API_KEY" value="${googleMap.etc3 }"  />
		</c:when>
	</c:choose>
</c:forEach>
					<form id="mapgoogleform" action="updateLocationMap.htm" method="post" class="form-body form-horizontal ">
							<input type="hidden" name="mode" value="update" />
							<input type="hidden" name="contentId" id="map_contentId" />
							<div class="row">
								<div class="col-md-12">
									<div class="form-group">
										<div id="gmap_knowledgeBase" class="form-control gmaps col-md-12" style="width:100%;height:350px">
											<c:choose>
												<c:when test="${ MAP_ENABLED == 'Y'}">
												<table style="height:100%;width:100%" ><tr><td align="center">Map Loading...</td></tr></table>
												</c:when>
												<c:otherwise>
												<table style="height:100%;width:100%" ><tr><td align="center">Map Disabled</td></tr></table>
												</c:otherwise>
											</c:choose>
										</div>
									</div>
								</div>
							</div>
							<div class="row">
								<div class="col-md-12">
									<div class="form-group">
										<label class="control-label col-md-2"><spring:message code="button.search.label" /></label>
										<div class="col-md-10">
											<input type="text" class="form-control " id="search_map" value="" readonly  />
										</div>
									</div>
								</div>
							</div>			
							<div class="row">
								<div class="col-md-6">
									<div class="form-group">
										<label class="control-label col-md-4"><spring:message code="knowledge.tab.map.lat" /></label>
										<div class="col-md-8">
											<input type="text" class="form-control inputfloat" id="map_lat" name="map_lat" value="" required maxlength="20"/>
										</div>
									</div>
								</div>
								<div class="col-md-6">
									<div class="form-group">
										<label class="control-label col-md-4"><spring:message code="knowledge.tab.map.lng" /></label>
										<div class="col-md-8">
											<input type="text" class="form-control inputfloat" id="map_lng" name="map_lng" value="" required maxlength="20"/>
										</div>
									</div>
								</div>
							</div>
							<div class="row form-actions fluid">
								<div class="col-md-12">
									<div class="col-md-offset-3 col-md-9 text-right">
										<div class="btn-group"> 
											<button type="button" class="btn blue disabled" id="btn_mapSave" name="btn_mapSave">
												<i class="fa fa-floppy-o"></i>											
												<spring:message code="button.save.label"/>
											</button>
										</div>
										
										<div class="btn-group">	
											<button type="button" class="btn default" id="btn_mapCancel" name="btn_mapCancel">
												<spring:message code="button.cancel.label"/>
											</button>
										</div>
										
										
									</div>
								</div>
							</div>	
						</form>
<c:if test="${ MAP_ENABLED == 'Y'}">
<script src="https://maps.googleapis.com/maps/api/js?v=3.exp&signed_in=true&libraries=places"></script>
<script type="text/javascript">
//Google Maps function 
var lat = $("#map_lat").val();
var lng = $("#map_lng").val();
var dlat = "13.7694359";
var dlng = "100.5737182";
var map = null;
var iconnew = "http://mt.google.com/vt/icon/text=new&psize=11&font=fonts/arialuni_t.ttf&color=ff330000&name=icons/spotlight/spotlight-waypoint-b.png&ax=44&ay=48&scale=1";
var iconold = "http://mt.google.com/vt/icon/text=old&psize=11&font=fonts/arialuni_t.ttf&color=ff330000&name=icons/spotlight/spotlight-waypoint-a.png&ax=44&ay=48&scale=1";
var marker = null;
function initMap(){
	if (lat == "") {
		lat = dlat;
		lng = dlng;
	}else{
		$("#map_lat").val(lat);
		$("#map_lng").val(lng);
	}
	MapGoogleV3.init();
	
	$("#map_lng").on("keyup",function(e){
		if(e.which==190){return;}
		if($("#map_lng").val()=="" || $("#map_lat").val()==""){return;}
		changeDataInputLatLng();
	});
	
	$("#map_lat").on("keyup",function(e){
		if(e.which==190){return;}
		if($("#map_lng").val()=="" || $("#map_lat").val()==""){return;}
		changeDataInputLatLng();
	});
}
function changeDataInputLatLng(){
	marker.setVisible(false);
	var latlng = new google.maps.LatLng($("#map_lat").val(),$("#map_lng").val());
	marker.setPosition(latlng);
	marker.setVisible(true);
	map.setCenter(latlng);
	map.setZoom(17);
}
var MapGoogleV3 = function(){
	var createMap = function(){
		var mapOptions = {
			center: new google.maps.LatLng(lat, lng),
			zoom: 13
		};
		map = new google.maps.Map($("#gmap_knowledgeBase")[0],mapOptions);
		
		// map search
		var input = $("#search_map")[0];
		var autocomplete = new google.maps.places.Autocomplete(input);
		autocomplete.bindTo('bounds', map);
		
		marker = new google.maps.Marker({
			map: map,
			draggable:true,
		    animation: google.maps.Animation.DROP
		});
		google.maps.event.addListener(marker, 'dragend', function(){
			var latlng = (""+marker.position).substring(1,(""+marker.position).length-1).split(", ");
			console.log("lat:"+latlng[0]);
			console.log("lng:"+latlng[1]);
			if (latlng[0] != dlat) {
				$("#map_lat").val(latlng[0]);
				$("#map_lng").val(latlng[1]);
			}
		});
		google.maps.event.addListener(map, 'click', function(event){
			marker.setPosition(event.latLng);
			marker.setVisible(true);
			console.log(""+event.latLng);
			var latlng = (""+event.latLng).substring(1,(""+event.latLng).length-1).split(", ");
			$("#map_lat").val(latlng[0]);
			$("#map_lng").val(latlng[1]);
			map.setCenter(event.latLng);
		});
		google.maps.event.addListener(autocomplete, 'place_changed', function() {
			
			var place = autocomplete.getPlace();
			if (!place.geometry) {
				alertMessage("<spring:message code="knowledge.tab.Map" />","<spring:message code="knowledge.tab.map.selectplace" />");
				$("#search_map").focus();
				return;
			}
			marker.setVisible(false);
			// If the place has a geometry, then present it on a map.
			if (place.geometry.viewport) {
				map.fitBounds(place.geometry.viewport);
			} else {
				map.setCenter(place.geometry.location);
				map.setZoom(17);  // Why 17? Because it looks good.
			}
			
			marker.setPosition(place.geometry.location);
			marker.setVisible(true);
			var latlng = (""+marker.position).substring(1,(""+marker.position).length-1).split(", ");
			$("#map_lat").val(latlng[0]);
			$("#map_lng").val(latlng[1]);
		});

	};
	return {
		init : function() {
			createMap();
		}
	};
}();

function resetMap() {
	mapReadOnly(false);
	marker.setVisible(false);
	marker.setPosition(new google.maps.LatLng(lat,lng));
	if (lat != dlat) {
		$("#map_lat").val(lat);
		$("#map_lng").val(lng);
		map.setZoom(17);
	} else {
		$("#map_lat").val("");
		$("#map_lng").val("");
		map.setZoom(10);
	}
	map.setCenter(new google.maps.LatLng(lat,lng));
}
function changeMap(latlng) {
	mapReadOnly(false);
	marker.setVisible(false);
	latlng = latlng.split(",");
	$("#map_lat").val(latlng[0]);
	$("#map_lng").val(latlng[1].trim());
	lat = $("#map_lat").val();
	lng = $("#map_lng").val();
	marker.setPosition(new google.maps.LatLng(lat,lng));
	map.setCenter(new google.maps.LatLng(lat,lng));
	map.setZoom(17);
	marker.setVisible(true);
	
}

function mapReadOnly(flag){
	if(flag){
		$("#btn_mapSave").addClass("disabled");
	}else{
		$("#btn_mapSave").removeClass("disabled");
	}
	$("#map_lat").attr("readonly",flag);
	$("#map_lng").attr("readonly",flag);
	$("#search_map").attr("readonly",flag);
	$("#map_lat").val("");
	$("#map_lng").val("");
	$("#search_map").val("");
}
// end Google Maps
</script>
</c:if>

<script type="text/javascript">
	jQuery(document).ready(function() {
		$("#btn_mapCancel").hide();
	});
$("#btn_mapSave").on("click",function(){
	if (!$("#mapgoogleform").valid()) {
		return false;
	}
	$("#map_contentId").val($("#contentNumber").val());
	ajaxSubmitForm($("#mapgoogleform"),function(data) {
		if(data.success==true){
			$("#search_map").val("");
		}else{
			alertMessage("บันทึกแผนที่","<spring:message code="lbl.save.fail" />");
		}
		
	});
});
</script>