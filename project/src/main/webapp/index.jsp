<!DOCTYPE html>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ include file="/WEB-INF/view/template/header.jsp" %>




        <div class="content-header">
          <h1>
          	두 번째 프로젝트 : 웹페이지 개발
            <small>Current version : Test</small>
          </h1>
        </div>

        <!-- Main content -->
        <div class="content body">
          <div class="callout callout-warning">
            <h4>주의!</h4>
            	이 홈페이지는 아직 미완성입니다. 완성되면 좀 더 굉장해질 것입니다.
          </div>
          <section id='introduction'>
            <h2 class='page-header'><span>홈페이지 소개</span></h2>
            <p class='lead'>
            	<b>이 홈페이지</b>는 강의 사이트 입니다.
            	초기 디자인은 원격제어를 통해 더욱 심화적인 1:1 또는 1:다수의 강의와, 추가적으로 동영상 강의를 지원할 생각이였습니다.
            	그러나 원격 제어는 기술적으로 무리였고, 동영상 스트리밍만 구현이 되어있습니다.
              	이 홈페이지의 전체적인 디자인은 오픈소스를 사용하여 만들어졌습니다.
              	이 HTML template은 CSS framework Bootstrap 3를 기반으로 만들어졌고 4의 기능을 몇 가지 추가하였습니다.
              	이 홈페이지는 Bootstrap에서의 모든 components를 사용하였고, 일반적으로 많이 사용되고 있는 플러그인을 사용하여 재 디자인 하였습니다.
            </p>
          </section>

          <section id='download'>
            <pre class='hierarchy'><code class="language-bash" data-lang="bash">사용한 소스코드 패키지 파일의 계층 구조

AdminLTE/
├── dist/
│   ├── CSS/
│   ├── JS
│   ├── img
├── build/
│   ├── less/
│   │   ├── AdminLTE's Less files
│   └── Bootstrap-less/ (Only for reference. No modifications have been made)
│       ├── mixins/
│       ├── variables.less
│       ├── mixins.less
└── plugins/
    ├── All the customized plugins CSS and JS files</code></pre>
          </section>

          <section id="dependencies">
            <h2 class="page-header"><span>Dependencies</span></h2>
            <p class="lead">
				이 오픈소스는 두 가지 기본 프레임 워크에 의존합니다.
            </p>
            <ul class="lead">
              <li><a href="http://getbootstrap.com" target="_blank">Bootstrap 3</a></li>
              <li><a href="http://jquery.com/" target="_blank">jQuery 1.11+</a></li>
              <li><a href="#plugins">다른 모든 플러그인은 다음과 같습니다.</a></li>
            </ul>
          </section>

            <h3 id='component-main-header'>Main Header</h3>
            <p class='lead'>
				기본 헤더에는 로고와 탐색 표시 줄이 있습니다.
            </p>
            <div class="box box-solid">
              <div class="box-body" style="position: relative;">
                <span class="eg">메인 헤더</span>
                <header class="main-header" style="position: relative;">
                  <!-- Logo -->
                  <a href="index2.html" class="logo"><b>로고</b>넣는자리</a>
                  <!-- Header Navbar: style can be found in header.less -->
                  <nav class="navbar" role="navigation" style="border: 0;">
                    <!-- Sidebar toggle button-->
                    <a href="#" class="sidebar-toggle" role="button">
                      <span class="sr-only">Toggle navigation</span>
                    </a>
                  </nav>
                </header>
              </div>
            </div>
            

            <h3 id='component-sidebar'>Sidebar</h3>
            <p class="lead">
            	이 페이지의 왼쪽에 사용된 사이드바는 당신이 좋아할만한 기능들이 담겨져 있을것입니다.
            </p>

          <section id='plugins'>
            <h2 class='page-header'><span>Plugins</span></h2>
            <p class="lead">이 홈페이지는 다음 플러그인을 사용합니다. 설명서, 업데이트 또는 라이센스 정보는 제공된 링크를 참조하십시오.</p>
            <div class='row'>
              <div class='col-sm-3'>
                <ul class="list-unstyled">
                  <li><h4>Charts</h4></li>
                  <li><a href='http://chartjs.org/' target="_blank">ChartJS</a></li>
                  <li><a href='http://www.flotcharts.org/' target="_blank">Flot</a></li>
                  <li><a href='http://morrisjs.github.io/morris.js/' target="_blank">Morris.js</a></li>
                  <li><a href='http://omnipotent.net/jquery.sparkline/' target="_blank">Sparkline</a></li>
                </ul>
              </div><!-- /.col -->
              <div class='col-sm-3'>
                <ul class="list-unstyled">
                  <li><h4>Form Elements</h4></li>
                  <li><a href='https://github.com/seiyria/bootstrap-slider'>Bootstrap Slider</a></li>
                  <li><a href='http://ionden.com/a/plugins/ion.rangeSlider/en.html' target="_blank">Ion Slider</a></li>
                  <li><a href='http://bootstrap-datepicker.readthedocs.org/' target="_blank">Date Picker</a></li>
                  <li><a href='http://www.improvely.com/' target="_blank">Date Range Picker</a></li>
                  <li><a href='http://mjolnic.github.io/bootstrap-colorpicker/' target="_blank">Color Picker</a></li>
                  <li><a href='https://github.com/jdewit/bootstrap-timepicker' target="_blank">Time Picker</a></li>
                  <li><a href='http://git.io/arlzeA' target="_blank">iCheck</a></li>
                  <li><a href='http://github.com/RobinHerbots/jquery.inputmask' target="_blank">Input Mask</a></li>
                </ul>
              </div><!-- /.col -->
              <div class='col-sm-3'>
                <ul class="list-unstyled">
                  <li><h4>Editors</h4></li>
                  <li><a href='https://github.com/Waxolunist/bootstrap3-wysihtml5-bower' target="_blank">Bootstrap WYSIHTML5</a></li>
                  <li><a href='http://ckeditor.com' target="_blank">CK Editor</a></li>
                </ul>
              </div><!-- /. col -->
              <div class='col-sm-3'>
                <ul class="list-unstyled">
                  <li><h4>Other</h4></li>
                  <li><a href='https://datatables.net/examples/styling/bootstrap.html' target="_blank">Data Tables</a></li>
                  <li><a href='http://arshaw.com/fullcalendar/' target="_blank">Full Calendar</a></li>
                  <li><a href='http://jqueryui.com' target="_blank">jQuery UI</a></li>
                  <li><a href='http://anthonyterrien.com/knob/' target="_blank">jQuery Knob</a></li>
                  <li><a href='http://jvectormap.com/' target="_blank">jVector Map</a></li>
                  <li><a href='http://rocha.la' target="_blank">Slim Scroll</a></li>
                  <li><a href='http://github.hubspot.com/pace/docs/welcome/' target="_blank">Pace</a></li>
                </ul>
              </div><!-- /.col -->
            </div><!-- /.row -->
          </section>


          <section id='browsers'>
            <h2 class='page-header'><span>Browser Support</span></h2>
            <p class="lead">이 홈페이지는 하기의 부라우저들을 지원합니다</p>
            <ul>
              <li>IE9+</li>
              <li>FireFox (latest)</li>
              <li>Safari (latest)</li>
              <li>Chrome (latest)</li>
              <li>Opera (latest)</li>
            </ul>
            <p><b>주의사항:</b> IE9는 장면 전환이나 애니메이션을 지원하지 않습니다. 템플릿은 제대로 작동하지만 IE9에서는 애니메이션 / 전환을 사용하지 않습니다.</p>
          </section>


          <section id="license">
            <h1 class="page-header"><span>License</span></h1>
            <p class="lead">
            	이 홈페이지는 MIT 라이선스에 따라 사용이 허가 된 오픈 소스 프로젝트를 통해 제작되었습니다.
            </p>
          </section>
        </div><!-- /.content -->
        
<%@ include file="/WEB-INF/view/template/footer.jsp" %>              
