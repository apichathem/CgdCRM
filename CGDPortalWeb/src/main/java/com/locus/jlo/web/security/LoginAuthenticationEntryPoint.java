package com.locus.jlo.web.security;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.PortMapper;
import org.springframework.security.web.PortMapperImpl;
import org.springframework.security.web.PortResolver;
import org.springframework.security.web.PortResolverImpl;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.security.web.util.RedirectUrlBuilder;
import org.springframework.security.web.util.UrlUtils;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

public class LoginAuthenticationEntryPoint implements AuthenticationEntryPoint, InitializingBean {
	private static final Log logger = LogFactory.getLog(LoginUrlAuthenticationEntryPoint.class);

	private PortMapper portMapper = new PortMapperImpl();

	private PortResolver portResolver = new PortResolverImpl();
	private String loginFormUrl;
	private boolean forceHttps = false;

	private boolean useForward = false;

	private final RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();

	@Deprecated
	public LoginAuthenticationEntryPoint() {
	}

	public LoginAuthenticationEntryPoint(String loginFormUrl) {
		this.loginFormUrl = loginFormUrl;
	}

	public void afterPropertiesSet() throws Exception {
		Assert.isTrue((StringUtils.hasText(this.loginFormUrl)) && (UrlUtils.isValidRedirectUrl(this.loginFormUrl)),
				"loginFormUrl must be specified and must be a valid redirect URL");

		if ((this.useForward) && (UrlUtils.isAbsoluteUrl(this.loginFormUrl))) {
			throw new IllegalArgumentException("useForward must be false if using an absolute loginFormURL");
		}
		Assert.notNull(this.portMapper, "portMapper must be specified");
		Assert.notNull(this.portResolver, "portResolver must be specified");
	}

	protected String determineUrlToUseForThisRequest(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) {
		return getLoginFormUrl();
	}

	public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {

		boolean isAjax = "XMLHttpRequest".equals(request.getHeader("X-Requested-With"));
		logger.info("isAjax : " + isAjax);

		if (isAjax) {
			String jsonObject = "{\"status\": 401," + "\"message\":\"Access Denied.\"}";
			String contentType = "application/json;charset=UTF-8";
			response.setContentType(contentType);
			PrintWriter out = response.getWriter();
			out.print(jsonObject);
			out.flush();
			out.close();
			return;
		} else {
			String redirectUrl = null;
			if (this.useForward) {
				if ((this.forceHttps) && ("http".equals(request.getScheme()))) {
					redirectUrl = buildHttpsRedirectUrlForRequest(request);
				}

				if (redirectUrl == null) {
					String loginForm = determineUrlToUseForThisRequest(request, response, authException);

					if (logger.isDebugEnabled()) {
						logger.debug("Server side forward to: " + loginForm);
					}

					RequestDispatcher dispatcher = request.getRequestDispatcher(loginForm);

					dispatcher.forward(request, response);

					return;
				}
			} else {
				redirectUrl = buildRedirectUrlToLoginPage(request, response, authException);
			}
			this.redirectStrategy.sendRedirect(request, response, redirectUrl);
		}
	}

	protected String buildRedirectUrlToLoginPage(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) {
		String loginForm = determineUrlToUseForThisRequest(request, response, authException);

		if (UrlUtils.isAbsoluteUrl(loginForm)) {
			return loginForm;
		}

		int serverPort = this.portResolver.getServerPort(request);
		String scheme = request.getScheme();

		RedirectUrlBuilder urlBuilder = new RedirectUrlBuilder();

		urlBuilder.setScheme(scheme);
		urlBuilder.setServerName(request.getServerName());
		urlBuilder.setPort(serverPort);
		urlBuilder.setContextPath(request.getContextPath());
		urlBuilder.setPathInfo(loginForm);

		if ((this.forceHttps) && ("http".equals(scheme))) {
			Integer httpsPort = this.portMapper.lookupHttpsPort(Integer.valueOf(serverPort));

			if (httpsPort != null) {
				urlBuilder.setScheme("https");
				urlBuilder.setPort(httpsPort.intValue());
			} else {
				logger.warn("Unable to redirect to HTTPS as no port mapping found for HTTP port " + serverPort);
			}
		}

		return urlBuilder.getUrl();
	}

	protected String buildHttpsRedirectUrlForRequest(HttpServletRequest request) throws IOException, ServletException {
		int serverPort = this.portResolver.getServerPort(request);
		Integer httpsPort = this.portMapper.lookupHttpsPort(Integer.valueOf(serverPort));

		if (httpsPort != null) {
			RedirectUrlBuilder urlBuilder = new RedirectUrlBuilder();
			urlBuilder.setScheme("https");
			urlBuilder.setServerName(request.getServerName());
			urlBuilder.setPort(httpsPort.intValue());
			urlBuilder.setContextPath(request.getContextPath());
			urlBuilder.setServletPath(request.getServletPath());
			urlBuilder.setPathInfo(request.getPathInfo());
			urlBuilder.setQuery(request.getQueryString());

			return urlBuilder.getUrl();
		}

		logger.warn("Unable to redirect to HTTPS as no port mapping found for HTTP port " + serverPort);

		return null;
	}

	public void setForceHttps(boolean forceHttps) {
		this.forceHttps = forceHttps;
	}

	protected boolean isForceHttps() {
		return this.forceHttps;
	}

	@Deprecated
	public void setLoginFormUrl(String loginFormUrl) {
		this.loginFormUrl = loginFormUrl;
	}

	public String getLoginFormUrl() {
		return this.loginFormUrl;
	}

	public void setPortMapper(PortMapper portMapper) {
		this.portMapper = portMapper;
	}

	protected PortMapper getPortMapper() {
		return this.portMapper;
	}

	public void setPortResolver(PortResolver portResolver) {
		this.portResolver = portResolver;
	}

	protected PortResolver getPortResolver() {
		return this.portResolver;
	}

	public void setUseForward(boolean useForward) {
		this.useForward = useForward;
	}

	protected boolean isUseForward() {
		return this.useForward;
	}
}
