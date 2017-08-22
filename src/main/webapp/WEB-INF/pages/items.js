/**
 * 
 */

			var itemsStrArray = [];
			var index = 0;
			
			getPageData(true);
	
			$.get("/getLoggedUserName",function(data,status){
				document.getElementById("userNameLabel").innerHTML = "Hi, " + data;
			});
			
			$(document).ready(function(){
				  $('.modal-trigger').leanModal();
				});
			
			function getPageData(isInitData){
				$.get("/items",function(data,status){
					itemsStrArray = data.split("-");
					
					renderData1();
					renderData2();
					renderData3();
					
					if(isInitData == true){
						renderPageNumbersList();
					}
				});
			}
			
			
			function renderPageNumbersList(){
				var i;
				var pagesCount = itemsStrArray.length/3;
				if(itemsStrArray.length%3 != 0 && itemsStrArray.length>3){
					pagesCount++;
				}
				var numberListElement = document.getElementById("pageNumsList");
				
				for(i=2;i<=pagesCount;i++){
					var node = document.createElement("LI");
					node.setAttribute("id","page"+i);
					var nodeClick = document.createElement("A");
					nodeClick.innerHTML = i;
					node.appendChild(nodeClick);
					numberListElement.appendChild(node);
				}
				
				var rightArrownNode = document.createElement("LI");
				rightArrownNode.setAttribute("class","waves-effect");
				var nodeClick = document.createElement("a");
				nodeClick.setAttribute("href","javascript:nextPage()")
				var nodeIco = document.createElement("I");
				nodeIco.setAttribute("class","material-icons");
				nodeIco.innerHTML = "chevron_right";
				nodeClick.appendChild(nodeIco);
				rightArrownNode.appendChild(nodeClick); 
				numberListElement.appendChild(rightArrownNode);
			}
			
			function renderData1(){
				if(index < itemsStrArray.length){
					var itemStr = itemsStrArray[index];
					if(itemStr != ""){
						var itemArray = itemStr.split(",");
						var id = itemArray[0];
						var title = itemArray[1];
						var content = itemArray[2];
						var status = itemArray[3];

						document.getElementById("item1").style.display = "block";
						document.getElementById("title1").innerHTML = title;
						document.getElementById("content1").innerHTML = content;
						document.getElementById("status1").innerHTML = status;
						document.getElementById("itemId1").value = id;
						index++;
					}
				} else{
					document.getElementById("item1").style.display = "none";
				}
			}
			
			function renderData2(){
				if(index < itemsStrArray.length){
					var itemStr = itemsStrArray[index];
					if(itemStr != ""){
						var itemArray = itemStr.split(",");
						var id = itemArray[0];
						var title = itemArray[1];
						var content = itemArray[2];
						var status = itemArray[3];
						
						document.getElementById("item2").style.display = "block";
						document.getElementById("title2").innerHTML = title;
						document.getElementById("content2").innerHTML = content;
						document.getElementById("status2").innerHTML = status;
						document.getElementById("itemId2").value = id;
						index++;
					}
				} else{
					document.getElementById("item2").style.display = "none";
				}
			}
			function renderData3(){
				if(index < itemsStrArray.length){
					var itemStr = itemsStrArray[index];
					if(itemStr != ""){
						var itemArray = itemStr.split(",");
						var id = itemArray[0];
						var title = itemArray[1];
						var content = itemArray[2];
						var status = itemArray[3];
						
						document.getElementById("item3").style.display = "block";
						document.getElementById("title3").innerHTML = title;
						document.getElementById("content3").innerHTML = content;
						document.getElementById("status3").innerHTML = status;
						document.getElementById("itemId3").value = id;
						index++;
					}
				} else{
					document.getElementById("item3").style.display = "none";
				}
			}
			
			function nextPage(){
				if(index < itemsStrArray.length){
					$('#page'+(index/3)).removeClass("active");	
					$('#page'+((index+3)/3)).addClass("active");	
					renderData1();
					renderData2();
					renderData3();
				}
			}
			
			function prevPage(){
				if(index > 2){
					if(index%3 == 0){
						index -= 6;
					}else if(index%3 == 2){
						index -= 5;
					}else if(index%3 == 1){
						index -= 4;
					}
					
					renderData1();
					renderData2();
					renderData3();
					
					$('#page'+((index+3)/3)).removeClass("active");			
					$('#page'+index/3).addClass("active");
				}
			}
			
			function editItem(){
				var title = document.getElementById("titleModal").value;
				var content = document.getElementById("contentModal").value;
				var status = document.getElementById("statusModal").value;
				var id = document.getElementById("itemIdModal").value;
				
				var urlParams = "?title=" + title + "&content=" + content + "&status=" + status + "&id=" + id;
				$.post("editItem" + urlParams,function(data,status){
					if(data == true){
						index = 0;
						getPageData(false);
					}
				});
			}
			
			function editItemDialog(itemNum){
				var title = document.getElementById("titleModal");
				var content = document.getElementById("contentModal");
				var status = document.getElementById("statusModal");
				var id = document.getElementById("itemIdModal");
				
				title.value = document.getElementById("title" + itemNum).innerHTML;
				content.value = document.getElementById("content" + itemNum).innerHTML;
				status.value = document.getElementById("status" + itemNum).innerHTML;
				id.value = document.getElementById("itemId" + itemNum).value;
			}
			
			
			