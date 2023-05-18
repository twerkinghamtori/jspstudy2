<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="path" value="${pageContext.request.contextPath }" />

<!DOCTYPE html>
<html>
<head>
<title><sitemesh:write property="title"/></title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Raleway">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<style>
html,body,h1,h2,h3,h4,h5 {font-family: "Raleway", sans-serif}
</style>
<script type="text/javascript" src="http://cdn.ckeditor.com/4.5.7/full/ckeditor.js"></script>
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.9.4/Chart.min.js"></script>
<script type="text/javascript">
	function colorchange() {
		let x = document.getElementById("menu");
		x.setAttribute("class", "w3-bar-item w3-button w3-padding w3-blue");
	}
</script>
<sitemesh:write property="head"/>
</head>

<body class="w3-light-grey">

<!-- Top container -->
<div class="w3-bar w3-top w3-black w3-large" style="z-index:4">
  <button class="w3-bar-item w3-button w3-hide-large w3-hover-none w3-hover-text-light-grey" onclick="w3_open();"><i class="fa fa-bars"></i> &nbsp;Menu</button>
  <span class="w3-bar-item w3-right">
  	<c:if test="${empty sessionScope.login}">
  		<a href="${path }/member/loginForm">로그인</a>
  		<a href="${path }/member/joinForm">회원가입</a>  		
  	</c:if>
  	<c:if test="${!empty sessionScope.login }">
  	    <a href="${path }/member/main">${sessionScope.login }</a>님이 로그인하셨습니다.
  		<a href="${path }/member/logout">로그아웃</a>
  	</c:if>
  </span>
</div>

<!-- Sidebar/menu -->
<nav class="w3-sidebar w3-collapse w3-white w3-animate-left" style="z-index:3;width:300px;" id="mySidebar"><br>
  <div class="w3-container w3-row">
    <div class="w3-col s4">
      <img src="${path }/image/dochi.jpg" class="w3-circle w3-margin-right" style="width:80px">
    </div>
    <div class="w3-col s8 w3-bar">
      <span>Welcome, <strong>${sessionScope.login } !</strong></span><br>
      <a href="#" class="w3-bar-item w3-button"><i class="fa fa-envelope"></i></a>
      <a href="#" class="w3-bar-item w3-button"><i class="fa fa-user"></i></a>
      <a href="#" class="w3-bar-item w3-button"><i class="fa fa-cog"></i></a>
    </div>
  </div>
  <!--  
  <div class="w3-row w3-margin-top">
  	<c:if test="${!empty sessionScope.login }">
  		<span>반갑습니다.<strong>${sessionScope.login }님</strong></span>
  	</c:if>
  	<c:if test="${empty sessionScope.login }">
  		<span><strong>로그인이 필요합니다.</strong></span>	
  	</c:if>
  </div>
  -->
  <hr>
  <div class="w3-bar-block" onclick="colorchange()">
    <a href="#" class="w3-bar-item w3-button w3-padding-16 w3-hide-large w3-dark-grey w3-hover-black" onclick="w3_close()" title="close menu"><i class="fa fa-remove fa-fw"></i>&nbsp; Close Menu</a>
    <a href="${path }/member/main" class="w3-bar-item w3-button w3-padding"><i class="fa fa-users fa-fw"></i>&nbsp; 마이페이지</a>
    <a href="${path }/board/list?boardid=1" class="w3-bar-item w3-button w3-padding  <c:if test='${empty boardid || boardid==1 }'>w3-blue</c:if>"><i class="fa fa-eye fa-fw"></i>&nbsp; 공지사항</a>
    <a href="${path }/board/list?boardid=2" class="w3-bar-item w3-button w3-padding <c:if test='${boardid==2 }'>w3-blue</c:if>"><i class="fa fa-users fa-fw"></i>&nbsp; 자유게시판</a>
    <a href="${path }/board/list?boardid=3" class="w3-bar-item w3-button w3-padding <c:if test='${boardid==3 }'>w3-blue</c:if>"><i class="fa fa-bullseye fa-fw"></i>&nbsp; QnA</a>
  </div>
  <%--ajax를 이용하여 환율 정보 출력 --%>
  <div class="w3-container">
  	<div id="exchange"></div>
  </div>
</nav>


<!-- Overlay effect when opening sidebar on small screens -->
<div class="w3-overlay w3-hide-large w3-animate-opacity" onclick="w3_close()" style="cursor:pointer" title="close side menu" id="myOverlay"></div>

<!-- !PAGE CONTENT! -->
<div class="w3-main" style="margin-left:300px;margin-top:43px;">

  <!-- Header -->
  <header class="w3-container" style="padding-top:22px">
    <h5><b><i class="fa fa-dashboard"></i>공공데이터 융합 자바/스프링 개발자 과정(GDJ 62)</b></h5>
  </header> 
  
  <div class="w3-row-padding w3-margin-bottom">
  	<div class="w3-half">
  		<div class="w3-container w3-padding-16">
  			<div id="piecontainer" style="width:80%; border:1px solid #ffffff;">
  				<canvas id="canvas1" style="width:100%"></canvas>
  			</div>
  		</div>
  	</div>
  	<div class="w3-half">
  		<div class="w3-container w3-padding-16">
  			<div id="barcontainer" style="width:80%; border:1px solid #ffffff;">
  				<canvas id="canvas2" style="width:100%"></canvas>
  			</div>
  		</div>
  	</div>
  </div>
  
  <div class="w3-panel">
  	<sitemesh:write property="body"/>
  </div>
  <hr>
  <!-- Footer -->
  <footer class="w3-container w3-padding-16 w3-light-grey">
    <h4>구디아카데미</h4>
    <p>Powered by <a href="https://www.w3schools.com/w3css/default.asp" target="_blank">w3.css</a></p>
    <hr>
    <div>
    	<span id="si">
    		<select name="si" onchange="getText('si')">
    			<option value="">시/도를 선택하세요</option>
    		</select>
    	</span>
    	<span id="gu">
    		<select name="gu" onchange="getText('gu')">
    			<option value="">구/군을 선택하세요</option>
    		</select>
    	</span>
    	<span id="dong">
    		<select name="dong">
    			<option value="">동/리를 선택하세요</option>
    		</select>
    	</span>
    </div>
  </footer>

  <!-- End page content -->
</div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>


<script>
// Get the Sidebar
var mySidebar = document.getElementById("mySidebar");

// Get the DIV with overlay effect
var overlayBg = document.getElementById("myOverlay");

// Toggle between showing and hiding the sidebar, and add overlay effect
function w3_open() {
  if (mySidebar.style.display === 'block') {
    mySidebar.style.display = 'none';
    overlayBg.style.display = "none";
  } else {
    mySidebar.style.display = 'block';
    overlayBg.style.display = "block";
  }
}

// Close the sidebar with the close button
function w3_close() {
  mySidebar.style.display = "none";
  overlayBg.style.display = "none";
}
</script>

<script type="text/javascript">
	let randomColorFactor = function() {
		return Math.round(Math.random()*225)
	}
	let randomColor = function(opacity) { 
		return "rgba(" + randomColorFactor() + "," + randomColorFactor() + "," + randomColorFactor() + "," + (opacity || '.3') + ")"
	}
	
	$(function() {
		//작성자 별 게시물 건수
		piegraph();
		
		//최근 게시물 그래프
		bargraph(); 
		
		//ajax를 이용하여 환율 데이터 조회하기
		exchangeRate();
		
		//ajax를 이용하여 지역 데이터를 조회하기
		let divid;
		let si;
		$.ajax({
			url : "${path}/ajax/select",
			success : function(data) {
				let arr = JSON.parse(data)
				$.each(arr,function(i,item){
					//select[..] 선택자
					$("select[name=si]").append(function() {
						return "<option>" +item +"</option>"
					})
				})
			},
			error : function(e) {
				alert(e.status)
			}
		})
	})
	function getText(name) { //name= si || gu
		let city = $("select[name='si']").val()
		let gun = $("select[name='gu']").val()
		let disname
		let toptext = '구/군을 선택하세요'
		let params=''
		if(name=='si') {
			params = "si=" + city.trim()
			disname = "gu"
		} else if(name=='gu') {
			params = "si=" + city.trim() + "&gu=" + gun.trim()
			disname = "dong"
			toptext = '동/리를 선택하세요'
		} else {
			return;
		}
		$.ajax({
			url : "${path}/ajax/select",
			type : "POST",
			data : params,
			success : function(data) {
				console.log(data)
				let arr = JSON.parse(data)
				$("select[name=" + disname + "] option").remove()
				$("select[name=" + disname + "]").append(function(){
					return "<option>" + toptext + "</option>"
				})
				$.each(arr, function(i,item){
					$("select[name=" + disname + "]").append(function(){
						return "<option>" + item + "</option>"
					})
				})
			},
			error : function(e) {
				alert(e.status)
			}
		})
	}
	function exchangeRate() {
		$.ajax("${path}/ajax/exchange", {
			success : function(data) {
				$("#exchange").html(data)
			},
			error : function(e) {
				alert("환율조회"+e.status)
			}
		})
	}
	function piegraph() {
		$.ajax("${path}/ajax/graph1", {
			success : function(data) {
				pieGraphPrint(data);
			},
			error : function(e) {
				alert("파이 그래프" + e.status)
			}
		})
	}
	function pieGraphPrint(data) {
		console.log(data); //[{writer : '이름1', cnt : 갯수},...]
		let rows = JSON.parse(data);
		let writers = []; //x축의 내용 => labels로 들어감
		let datas = [];
		let colors = [];
		$.each(rows, function(i,item) {
			writers[i] = item.writer;
			datas[i] = item.cnt;
			colors[i] = randomColor(1);
		})
		let config = {
			type : 'pie',
			data : {
				datasets : [{
					data : datas,
					backgroundColor : colors
				}],
				labels : writers
			},
			options : {
				responsive : true,
				legend : {position : 'top'},
				title : {
					display : true,
					text : '게시물 작성자별 등록 건수',
					position : "bottom"
				}
			}
		}
		let ctx = document.getElementById("canvas1").getContext("2d")
		new Chart(ctx,config)
	}
	function bargraph() {
		$.ajax("${path}/ajax/graph2", {
			success : function(data) {
				barGraphPrint(data);
			},
			error : function(e) {
				alert("바 그래프" + e.status)
			}
		})
	}
	function barGraphPrint(data) {
		console.log(data); 
		let rows = JSON.parse(data);
		let regdates = []; 
		let datas = [];
		let colors = [];
		$.each(rows, function(i,item) {
			regdates[i] = item.regdate;
			datas[i] = item.cnt;
			colors[i] = randomColor(1);
		})
		let chartData = {
			labels : regdates,
			datasets : [{
				type : 'line',
				borderWidth : 2,
				borderColor : colors,
				label : '건수',
				fill : false,
				data : datas
			},{
				type : 'bar',
				backgroundColor : colors,
				label : '건수',
				data : datas
			}]
		}
		let config = {
			type : 'bar',
			data : chartData,
			options : {
				responsive : true,
				title : {
					display : true,
					text : '최근 7일 게시판 등록 건수',
					position : 'bottom'
				},
				legend : {display : false},
				scales : {
					xAxes : [{
						display : true,
						stacked : true
					}],
					yAxes : [{
						display : true,
						stacked : true
					}]
				}
			}
		}
		let ctx = document.getElementById("canvas2").getContext("2d")
		new Chart(ctx,config)
	}
</script> 
</body>
</html>