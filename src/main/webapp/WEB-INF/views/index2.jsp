<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<body>
<h2>Mood!</h2>

I am feeling: ${ mood.feeling }.

<a href="/one/reason?currentMood=${ mood.feeling }">Want to know why Tomcat is feeling so !!</a>

</body>
</html>