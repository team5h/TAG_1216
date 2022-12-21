<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="../header.jsp" %>

<link rel="preconnect" href="https://fonts.googleapis.com">
<link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
<link href="https://fonts.googleapis.com/css2?family=Jost:wght@100;200;300;400;500;600;700;800;900&family=Mulish:wght@200;300;400;500;600;700;800;900;1000&display=swap" rel="stylesheet">

<style>
	.leftcontent {
		float: left;
		width: 15%;
		margin-right: 5%;
		height: 100%;
	}
	
	.rightcontent {
		float: right;
		width: 80%;
		height: 100%;
		font-family: 'Jost', sans-serif;
  		letter-spacing: 0.25px;	
	}
	
	.leftcontent, .rightcontent, .box { border-top: 1px solid black; }
	
	.leftcontent, .rightcontent { border-bottom: 1px solid black; }
	
	.leftcontent div, .rightcontent { padding: 10px; }
	
	.box { margin-top: 10%; }
	
	.mylike_product {
		margin-top: 1%;
		/*border: solid 1px blue;*/
		height: 50%;
	}

	.mylike_product p {
		border-bottom: solid 3px black;
	}
	
	table {
		margin-left:auto; 
    	margin-right:auto;
	}
</style>

<!-- 본문 시작 -->

<div class="title">
	<h3 style="text-align: center;">관심 공연 / 관심 상품</h3>
</div>

<div class="leftcontent">
	<div><b>${s_m_id}</b> 님</div>
	<div><a href="/mypage/memberupdateG">회원 정보 수정</a></div>
	<div class="box">
		<div><b>MY티켓</b>
			<div><a href="/mypage/myticket">- 예매 확인 / 취소</a></div>	
		</div>
		<div><b>MY상품</b>
			<div><a href="/mypage/myproduct">- 상품 구매내역</a></div>	
			<div><a href="/mypage/myqna">- 나의 문의내역</a></div>
		</div>
		<div><b>좋아요</b>
			<div><a href="/mypage/mylike">- 관심 공연 / 관심 상품</a></div>
		</div>
		<div><b>MY쿠폰 / MY포인트</b>
			<div><a href="/couponDetail/detail">- 보유 쿠폰</a></div>
			<div><a href="/pointDetail/detail">- 보유 포인트</a></div>
		</div>
	</div>
</div><!-- leftcontent end -->


<div class="rightcontent">
	<div class=mylike_product>
		<p style="text-align: center;">관심 상품</p>
		
		<table border="1">
		<tr>
		<c:forEach var="row" items="${list_p}" varStatus="vs">
			<td id="product_box">
				<c:choose>
					<c:when test="${row.postername != null}">
						<a href="/product/${row.like_li}"><img src="/storage/${row.postername}" width="100px"></a>
					</c:when>
					<c:otherwise>
						등록된 사진 없음!!<br>
					</c:otherwise>
				</c:choose>
				<div id="product_name">
					<a href="/product/${row.like_li}">${row.pro_name}</a>
				</div>
			</td>
			<!-- 테이블 한줄에 3칸씩 -->
			<c:if test="${vs.count mod 3==0 }">
				<tr></tr>
			</c:if>
		</c:forEach>
		</tr>
		</table>
		
		<!-- 페이징 -->
		<div style="width: 100%; display: block; text-align: center;">	
			<c:if test="${requestScope.count > 0}">
				<c:set var="pageCount" value="${requestScope.totalPage}" />
				<c:set var="startPage" value="${requestScope.startPage}" />
				<c:set var="endPage" value="${requestScope.endPage}" />
		
		
					<!-- endPage는 10단위이기에 총 페이지가 end 페이지보다 작으면 그 수를 대입한다 -->
					<!-- 즉, 만약 최종 페이지 수가 22라면 30까지 표시할 필요가 없으니 엔드 페이지 수를 22로 맞춘다 -->
					<c:if test="${endPage > pageCount}">
						<c:set var="endPage" value="${pageCount}" />
					</c:if>
		
					<!-- startPage는 1, 11, 21 .. 이기에 1보다 크다면 이전 페이지 이동 가능-->
					<c:if test="${startPage > 1}">
						<a href="/mypage/all_p?pageNum=${startPage-1}">[이전]</a>
					</c:if>
		
					<!-- 현재 페이지 볼드체, 현재 페이지 외의 보이는 페이지 전부 이동 링크 걸기 -->
					<c:forEach var="i" begin="${startPage}" end="${endPage}">
						<c:choose>
							<c:when test="${pageNum == i}">
								<span style="font-weight: bold">${i}</span>
							</c:when>
							<c:when test="${pageNum != i}">
								<a href="/mypage/all_p?pageNum=${i}">${i}</a>
							</c:when>
						</c:choose>
					</c:forEach>
		
					<!-- endPage보다 총 페이지 수가 크다면 다음 pages로 이동 가능하다 -->
					<c:if test="${endPage < pageCount}">
						<a href="/mypage/all_p?pageNum=${startPage+5}">[다음]</a>
					</c:if>
			</c:if>
		</div>
		
	</div>
</div><!-- rightcontent end -->

<!-- 본문 끝 -->

<%@ include file="../footer.jsp" %>