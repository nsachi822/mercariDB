<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0"/>
  <!-- css -->
  <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet" 
    integrity="sha384-wvfXpqpZZVQGK6TAh5PVlGOfQNHSoD2xbE+QkPxCAFlNEevoEH3Sl0sibVcOQVnN" crossorigin="anonymous"/>
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
    integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous"/>
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css"
    integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous"/>
  <link rel="stylesheet" href="./mercari.css"/>
  <!-- script -->
  <!-- search form 連動 -->
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"
    integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
    
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-easing/1.3/jquery.easing.min.js"></script>

      

<title>Rakus Items</title>
</head>
<body>
  <!-- navbar -->
  <nav class="navbar navbar-inverse">
    <div class="navbar-header">
      <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
        <span class="icon-bar"></span>
      </button>
      <a class="navbar-brand" href="${pageContext.request.contextPath}/list">Rakus Items</a>
    </div>
    <div id="navbar" class="collapse navbar-collapse">
      <div>
        <ul class="nav navbar-nav navbar-right">
          <li><a id="logout" href="./login.html">Logout <i class="fa fa-power-off"></i></a></li>
        </ul>
        <p class="navbar-text navbar-right">
          <span id="loginName">user: userName</span>
        </p>
      </div>
    </div>
  </nav>

  <div id="main" class="container-fluid">
    <!-- addItem link -->
    <div id="addItemButton">
      <a class="btn btn-default" href="${pageContext.request.contextPath}/addform"><i class="fa fa-plus-square-o"></i> Add New Item</a>
    </div>

    <!-- search form -->
    <div id="forms">
      <form action="" class="form-inline" role="form">
        <div class="form-group">
          <input type="text" class="form-control" id="name" placeholder="item name" id="search-text"/>
        </div>
        <div class="form-group"><i class="fa fa-plus"></i></div>
       
        <div class="form-group">
          <select class="form-control" id="pcate" id="search-text">
          	<option class="msg" disabled selected data-subgroup="">- parentCategory -</option>
            <c:forEach var="parentCategory" items="${ParentCategoryList}"> 
            <option value="${parentCategory.parentId}" data-subgroup="${parentCategory.parentId}"><c:out value="${parentCategory.parentCategory}"/></option>
            </c:forEach>            
          </select>
            <input type ="hidden" name= "parentId" value="${parentCategory.parentId}"/>
          
            <script>
            $('#pcate').change(function() {
                var r = $('option:selected').val();
                
                console.log(r);
            })
            </script>
          
          <select class="form-control" id="ccate" id="search-text">
              <option class="msg" data-group="" disabled selected>- childCategory -</option> 
            <c:forEach var="childCategory" items="${ChildCategoryList}">
            <option value="${childCategory.parentId}" data-group="${childCategory.parentId}" data-subgroup="${childCategory.childId}" ><c:out value="${childCategory.childCategory}"/></option>
            </c:forEach>
          </select>
          <input type ="hidden" name= "childId" value="${childCategory.childId}"/>
                  
          
          <select class="form-control" id="gccate" id="search-text">
            <option class="msg" data-group="" disabled selected>- grandChildCategory -</option>
            <c:forEach var="grandChildCategory" items="${GrandChildCategoryList}">
            <option value="${grandChildCategory.parentId}" data-group="${grandChildCategory.parentId}"><c:out value="${grandChildCategory.grandChildCategory}"/></option>
            </c:forEach>            
          </select>
        </div>
        
        <div class="form-group"><i class="fa fa-plus"></i></div>
        <div class="form-group">
          <input type="text" class="form-control" placeholder="brand"/>
        </div>
        <div class="form-group"></div>
        <button type="submit" class="btn btn-default"><i class="fa fa-angle-double-right"></i> search</button>
      </form>
    </div>
    
    <!-- pagination -->
    <div class="pages">
      <nav class="page-nav">
        <ul class="pager">
          <li class="previous"><a href="#">&larr; prev</a></li>
          <li class="next"><a href="#">next &rarr;</a></li>
        </ul>
      </nav>
    </div>
    <!-- table -->
    <div class="table-responsive">
      <table id="item-table" class="table table-hover table-condensed">
        <thead>
          <tr>
            <th>name</th>
            <th>price</th>
            <th>category</th>
            <th>brand</th>
            <th>cond</th>
          </tr>
        </thead>
        <tbody>
         <c:forEach var="item" items="${itemList}"  >
          <tr>
          <td class="item-name">
          <a href="${pageContext.request.contextPath}/detail/${item.itemName}/">
          <c:out value="${item.itemName}"/></a></td>
            <td class="item-price"><fmt:formatNumber value="${item.itemPrice}" pattern="0.0" /><br>
            <td class="item-category"><c:out value="${item.category}"/></td>
            <td class="item-brand"><c:out value="${item.brand}"/></td>
            <td class="item-condition"><c:out value="${item.conditionId}"/></td>
          </tr>
          </c:forEach>
          
            </tbody>
      </table>
    </div>

    <!-- pagination -->
    <div class="pages">
      <nav class="page-nav">
        <ul class="pager">
          <li class="previous"><a href="#">&larr; prev</a></li>
          <li class="next"><a href="#">next &rarr;</a></li>
        </ul>
      </nav>
      <!-- ページ番号を指定して表示するフォーム -->
      <div id="select-page">
        <form class="form-inline">
          <div class="form-group">
            <div class="input-group col-xs-6">
              <label></label>
              <input type="text" class="form-control"/>
              <!-- 総ページ数 -->
              <div class="input-group-addon">/ 20</div>
            </div>
            <div class="input-group col-xs-1">
              <button type="submit" class="btn btn-default">Go</button>
            </div>
          </div>
        </form>
      </div>
    </div>
  </div> 
  
  
  <script>
  
  setHierarchySelectEvent('#pcate', '#ccate');
  setHierarchySelectEvent('#ccate', '#gccate');
  //セレクトボックスの初期値
  $('#pcate').val("${parentCategory.parentId}").change();
  $('#ccate').val("${childCategory.parentId}").change();
  $('gccate').val("${childCategory.parentId}").change();
  
  function setHierarchySelectEvent(parentSelect, childSelect){
	    var initCategorySmallHtml = $(childSelect).html();
	    $(parentSelect).change(function(){
	        if( 1 < $(this).find('option:selected').length ){//複数選択時に処理
	            $(childSelect).find("option").each(function(index, element){
	                $(element).remove();
	            });
	        }else{
	            var subgroup =  $(this).find('option:selected').attr('data-subgroup');
	            $(childSelect).html(initCategorySmallHtml);
	            $(childSelect).find("option").each(function(index, element){
	                var group = $(element).attr('data-group');
	                if( group ){
	                    if( subgroup == group ){
	                        //$(element).css('display', 'block');//IEではoptionタグに対してdisplayは効かないため
	                    }else{
	                        //$(element).css('display', 'none');//IEではoptionタグに対してdisplayは効かないため
	                        $(element).remove();
	                    }
	                }
	            });
	        }
	        $(childSelect).val('').change();//未選択時の値は''じゃない場合は書き換えてね
	    });
	}

  </script>
  
  <script>
  function get_selected_input_items(name) {
	    var searchData = [];
	    $('[name=' + name + ']:checked').each(function() {
	        searchData.push($(this).val());
	    });
	    return searchData;
	}
  
  $(function () {
	  searchWord = function(){
	    var searchText = $(this).val(), // 検索ボックスに入力された値
	        targetText;

	    $('#item-table td').each(function() {
	      targetText = $(this).text();

	      // 検索対象となるリストに入力された文字列が存在するかどうかを判断
	      if (targetText.indexOf(searchText) != -1) {
	        $(this).removeClass('hidden');
	      } else {
	        $(this).addClass('hidden');
	      }
	    });
	  };

	  // searchWordの実行
	  $('#search-text').on('input', searchWord);
	});
  </script>
  

 
</body>
</html>