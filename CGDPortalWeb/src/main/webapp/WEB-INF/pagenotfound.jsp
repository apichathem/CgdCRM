<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

   <!-- BEGIN PAGE LEVEL PLUGIN STYLES --> 
   <link href="<%=request.getContextPath() %>/assets/css/page404.css" rel="stylesheet" type="text/css"/> 
   <link href="<%=request.getContextPath() %>/assets/css/font-opensans.css" rel="stylesheet" type="text/css"/>
<link href="<%=request.getContextPath() %>/assets/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
<link href="<%=request.getContextPath() %>/assets/plugins/uniform/css/uniform.default.css" rel="stylesheet" type="text/css"/>
<link href="<%=request.getContextPath() %>/assets/css/style-metronic.css" rel="stylesheet" type="text/css"/>
<link href="<%=request.getContextPath() %>/assets/css/style.css" rel="stylesheet" type="text/css"/>

    <!-- BEGIN PAGE CONTAINER -->  
    <div class="page-container">
  
       

        <!-- BEGIN CONTAINER -->   
        <div class="container margin-bottom-40">
          <div class="row">
            <div class="col-md-12 page-404">
               <div class="number">
                  404
               </div>
               <div class="details">
                  <h3>Oops!  You're lost.</h3>
                  <p>
                     We can not find the page you're looking for.<br>
                     <a href="javascript:window.history.back();">Go back</a>
                  </p>
                  <form action="#">
                     <div class="input-group input-medium">
                        <!-- <input type="text" placeholder="keyword..." class="form-control">
                        <span class="input-group-btn">                   
                        <button class="btn blue" type="submit"><i class="fa fa-search"></i></button>
                        </span> -->
                     </div>
                     <!-- /input-group -->
                  </form>
               </div>
            </div>
          </div>
        </div>
        <!-- END CONTAINER -->

  </div>
    <!-- END PAGE CONTAINER -->  

   


    <!--  Load javascripts at bottom, this will reduce page load time 
    <script src="assets/plugins/jquery-1.10.2.min.js" type="text/javascript"></script>
    <script src="assets/plugins/jquery-migrate-1.2.1.min.js" type="text/javascript"></script>
    <script src="assets/plugins/bootstrap/js/bootstrap.min.js" type="text/javascript"></script>      
    <script type="text/javascript" src="assets/plugins/hover-dropdown.js"></script>
    <script type="text/javascript" src="assets/plugins/back-to-top.js"></script>    
     <script type="text/javascript" src="assets/plugins/fancybox/source/jquery.fancybox.pack.js"></script> -->
      