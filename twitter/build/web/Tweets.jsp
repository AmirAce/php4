<%-- 
    Document   : Home
    Created on : Apr 21, 2022, 11:21:56 PM
    Author     : aamir
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>News Feed</title>
    </head>
    <body>
        <table>
            <tr>
                
                <th>Tweet</th>
                <th>From</th>
                <th>On</th>
                 <th>Likes</th>
            </tr>
            <c:forEach var="userfeed" items="${feed}">
                <tr>
                    <td><c:out value="${userfeed.text}" /></td>
                    <td><c:out value="${userfeed.user_id}" /></td>
                    <td><c:out value="${userfeed.timestamp}" /></td>
                    <td><c:out value="${userfeed.likes}" /></td>
                     
                </tr>
            </c:forEach>
        </table>
    


    
        <h2>Welcome ${username}!</h2>
        <c:if test="${(filename != null)}">
            <img src="GetImage?username=${username}" width="240" height="300"/>
        </c:if>
        <h3>Create a Text!</h3>
        <form action="Twitter" method="post" enctype="multipart/form-data">
            <div id="data">
                <input type="text" name="usertweet">
            </div>
            <div id="buttons">
                <input type="hidden" name="action" value="createTweet"/>
                <input type="submit"  value="Create"><br>
            </div>
            <h2>Create a Image!!</h2>
              <form action="Upload" method="post" enctype="multipart/form-data">
            <div id="data">
                <input type="file" accept="image/*" name="file">
            </div>
                  <div id="buttons">
                <label>&nbsp;</label>
                <input type="submit" value="Upload"><br>
            </div>
        </form>
         <form action="Twitter" method="post" enctype="multipart/form-data">
            
            <div id="buttons">
                <input type="hidden" name="action" value="listTweets"/>
                <input type="submit"  value="View"><br>
            </div>
        </form>
        <h3>Like Tweet</h3>
        <form action="Twitter" method="post" enctype="multipart/form-data">
            <div id="data">
                <label>ID</label>
                <input type="text" name="likedtweet">
            </div>
            <div id="buttons">
                <input type="hidden" name="action" value="tweetliked"/>
                <input type="submit"  value="Like"><br>
            </div>
        </form>
        <form>
            <h3>Follow User</h3>
            <input type ="text" name="followuser"/>
            <input type="hidden" name="action" value="userfollowed"/>
            <input type="submit"  value="follow"><br>
        </form>
         <form>
            <h3>Unfollow User</h3>
            <input type ="text" name="followuser"/>
            <input type="hidden" name="action" value="unfollow"/>
            <input type="submit"  value="unfollow"><br>
        </form>
    </body>