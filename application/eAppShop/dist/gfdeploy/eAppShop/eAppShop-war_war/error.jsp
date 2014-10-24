<%@ page contentType="application/json" pageEncoding="UTF-8"%>
{
    "messages": [
    {"text":<%=request.getAttribute("javax.servlet.error.message") %>, "severity": "error", "title" : <%=request.getAttribute("javax.servlet.error.status_code") %>}
  ]
}
