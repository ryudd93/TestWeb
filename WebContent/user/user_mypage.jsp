<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 
<%@ include file = "../include/header.jsp" %>
    
<section>
        <div class="container">
            <div class="row join-wrap">
                
                <div class="col-xs-12 col-md-9 join-form">
                    
                    <div class="titlebox">
                        MEMBER                   
                    </div>
                    <div>
                        <p>${sessionScope.user.name }님 회원정보</p>
                    </div>
                    <div>
                        <button type="button" class="btn btn-primary" onclick="location.href='mypageinfo.user'">회원정보변경</button>
                        <button type="button" class="btn btn-primary" name="password" id="delCheck">회원 탈퇴</button>
                        
                    </div>
                    <div class="delete-hidden" >
                        <form action="delete.user">
                        <input type="password" class="form-control" name="password" placeholder="비밀번호를 입력하세요">
                        <button type="button" class="btn btn-primary">확인</button>
                        </form>
                    </div>
                    
                    <br>
                    <div>
                        <p>${sessionScope.user.name }님의 작성 게시물</p>
                        <table class="table table-striped" style="text-align: center; border: 2px solid #737373">
                    <thead>
                        <tr>
                            <th style="text-align: center;">번호</th>
                            <th style="text-align: center;">제목</th>
                            <th style="text-align: center;">작성자</th>
                            <th style="text-align: center;">작성일</th>
                        </tr>
                    </thead>
                    
                    <tbody>
                    <c:forEach var="list" items="${list }">
                        <tr>
                            <td>${list.bno}</td>
                            <td><a href="/TestWeb/board/bbs_content.board?bno=${list.bno }">${list.title}</a></td>
                            <td>${list.writer}</td>
                            <td>${list.regdate}</td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
                    </div>
                    
                    
                </div>


            </div>

        </div>

    </section>
    
    <script>
        //탈퇴버튼 디스플레이 처리
        var delCheck = document.getElementById("delCheck");
        delCheck.onclick = function() {
            var del  = document.querySelector(".delete-hidden");
            if(del.style.display == "none" || del.style.display == "") {
                del.style.display = "inline";
            } else {
                del.style.display = "none";
            }
        }
    </script>


<%@ include file = "../include/footer.jsp" %>