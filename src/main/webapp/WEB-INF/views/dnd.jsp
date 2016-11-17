<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

<style>
.fileDrop {
   width: 100%;
   height: 300px;
   border: 3px dotted blue;
}
</style>

<div class='uploadedList'></div>

<form action='tsetR' method="post">

   <p><input type='text' name='uid'></p>
   <p><input type='text' name='upw'></p>
   <p><input type='hidden' name='uphoto' id='uphoto'></p>
   <p><button>REGISTER</button>
</form>

<div class='fileDrop'>
Drop Here
</div>


<script
  src="https://code.jquery.com/jquery-2.2.4.js"
  integrity="sha256-iT6Q9iMJYuQiMWNd9lDyBUStIq/8PuOW33aOqmvFpqI="
  crossorigin="anonymous"></script>

<script>
$(document).ready(function(){
   
    var uploadedList = $(".uploadedList")
   
   //("dragenter dragover", function(event) 이런식으로 이벤트를 동시에 2개 실행시킬수있다.
   $(".fileDrop").on("dragenter dragover", function(event){
   event.preventDefault();
   });
   $(".fileDrop").on("drop", function(event){
   event.preventDefault();
   
   //dataTransfer는 외부에서만 사용한다
   var files = event.originalEvent.dataTransfer.files;
   
   var file = files[0];
   
   console.log(file);
   
   //js코드로 form tag를 만들어내는거.브라우저 지원의 한계성이 많다
   var formData = new FormData();
   formData.append("file", file);
   
   console.log(formData);
   
   //이걸 쓰고 옵션을 줘야 동작한다
   $.ajax({
      url:"uploadFile",
      data:formData,// formData를 쓰면 기본적으로 multipart타입
      dataType:'text',
      type:"post",
      contentType:false,
      processData:false,
      success:function(data){
         console.log(data);
         //alert(data);
         uploadedList.html("<img src = show?name="+data+">");
         $("#uphoto").val(data);
      }
   });
   
   });
   
});

   
   
</script>

</body>
</html>