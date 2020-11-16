<%@page import="TestWeb.user.model.UserVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   
<%@ include file = "../include/header.jsp" %>

<section>
        <div class="container">
            <div class="row join-wrap">
                <!--join-form을 적용한다 float해제 margin:0 auto-->
                <div class="col-xs-12 col-md-9 join-form">
                    
                    <div class="titlebox">
                        MEMBER INFO                    
                    </div>
                    
                    <p>*표시는 필수 입력 표시입니다</p>
                    <form action="update.user" method="post">
                    <table class="table">
                        <tbody class="m-control">
                            <tr>
                                <td class="m-title">*ID</td>
                                <td><input class="form-control input-sm" name="id" value="${sessionScope.user.id }" readonly></td>
                            </tr>
                            <tr>
                                <td class="m-title">*이름</td>
                                <td><input class="form-control input-sm" name="name" value="${sessionScope.user.name }"></td>
                            </tr>
                            <tr>
                                <td class="m-title">*비밀번호</td>
                                <td><input class="form-control input-sm" name="password"></td>
                            </tr>
                            <tr>
                                <td class="m-title">*비밀번호확인</td>
                                <td><input class="form-control input-sm"></td>
                            </tr>
                            <tr>
                                <td class="m-title">*E-mail</td>
                                <td>
                                    <input class="form-control input-sm" name="email_id" value="${sessionScope.user.email }">@
                                    <select class="form-control input-sm sel" name="domain">
                                        <option value="naver">naver.com</option>
                                        <option value="gmail">gmail.com</option>
                                        <option value="daum">daum.net</option>
                                    </select>
                                    <button class="btn btn-info">중복확인</button>
                                </td>
                            </tr>
                            <tr>
                                <td class="m-title">*휴대폰</td>
                                <td>
                                    <input class="form-control input-sm sel" name = "ph_number1"> -
                                    <input class="form-control input-sm sel" name = "ph_number2"> -
                                    <input class="form-control input-sm sel" name = "ph_number3">
                                </td>
                            </tr>
                            <tr>
                                <td class="m-title">*주소</td>
                                <td><input class="form-control input-sm add" name="addr-basic" value="${sessionScope.user.addr_basic }"></td>
                            </tr>
                            <tr>
                                <td class="m-title">*상세주소</td>
                                <td><input class="form-control input-sm add" name="addr-detail" value="${sessionScope.user.addr_detail }"></td>
                            </tr>
                        </tbody>
                    </table>
                    
                    
                    <br/>
					<span>${msg }</span>
					<br/>
					
                    
                    <div class="titlefoot">
                        <button type="submit" class="btn">수정</button>
                        <button type="button" class="btn" onclick="history.go(-1)">목록</button>
                    </div>
                    </form>
                </div>


            </div>

        </div>

    </section>
    
<%@ include file = "../include/footer.jsp" %>