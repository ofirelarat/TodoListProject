function getPageData(e){index=0,$.get("/items",function(t,n){itemsStrArray=t.split("-"),renderData1(),renderData2(),renderData3(),1==e&&renderPageNumbersList()})}function renderPageNumbersList(){var e,t=itemsStrArray.length/3;itemsStrArray.length%3!=0&&t++;var n=document.getElementById("pageNumsList"),d=document.createElement("LI");d.setAttribute("class","waves-effect");var a=document.createElement("a");a.setAttribute("href","javascript:prevPage()");var i=document.createElement("I");for(i.setAttribute("class","material-icons"),i.innerHTML="chevron_left",a.appendChild(i),d.appendChild(a),n.appendChild(d),e=1;e<=t;e++){var m=document.createElement("LI");m.setAttribute("id","page"+e),1==e&&m.setAttribute("class","active"),(a=document.createElement("A")).innerHTML=e,m.appendChild(a),n.appendChild(m)}var l=document.createElement("LI");l.setAttribute("class","waves-effect"),(a=document.createElement("a")).setAttribute("href","javascript:nextPage()"),(i=document.createElement("I")).setAttribute("class","material-icons"),i.innerHTML="chevron_right",a.appendChild(i),l.appendChild(a),n.appendChild(l)}function renderData1(){if(index<itemsStrArray.length){var e=itemsStrArray[index];if(""!=e){var t=e.split(","),n=t[0],d=t[1],a=t[2],i=t[3];document.getElementById("item1").style.display="block",document.getElementById("title1").innerHTML=d,document.getElementById("content1").innerHTML=a,document.getElementById("status1").innerHTML=i,document.getElementById("itemId1").value=n,index++}}else document.getElementById("item1").style.display="none"}function renderData2(){if(index<itemsStrArray.length){var e=itemsStrArray[index];if(""!=e){var t=e.split(","),n=t[0],d=t[1],a=t[2],i=t[3];document.getElementById("item2").style.display="block",document.getElementById("title2").innerHTML=d,document.getElementById("content2").innerHTML=a,document.getElementById("status2").innerHTML=i,document.getElementById("itemId2").value=n,index++}}else document.getElementById("item2").style.display="none"}function renderData3(){if(index<itemsStrArray.length){var e=itemsStrArray[index];if(""!=e){var t=e.split(","),n=t[0],d=t[1],a=t[2],i=t[3];document.getElementById("item3").style.display="block",document.getElementById("title3").innerHTML=d,document.getElementById("content3").innerHTML=a,document.getElementById("status3").innerHTML=i,document.getElementById("itemId3").value=n,index++}}else document.getElementById("item3").style.display="none"}function nextPage(){index<itemsStrArray.length&&($("#page"+index/3).removeClass("active"),$("#page"+(index+3)/3).addClass("active"),renderData1(),renderData2(),renderData3())}function prevPage(){index>3&&(index%3==0?index-=6:index%3==2?index-=5:index%3==1&&(index-=4),renderData1(),renderData2(),renderData3(),$("#page"+(index+3)/3).removeClass("active"),$("#page"+index/3).addClass("active"))}function editItem(){var e="?title="+document.getElementById("titleModal").value+"&content="+document.getElementById("contentModal").value+"&status="+document.getElementById("statusModal").value+"&id="+document.getElementById("itemIdModal").value;$.post("editItem"+e,function(e,t){1==e&&getPageData(!1)})}function editItemDialog(e){var t=document.getElementById("titleModal"),n=document.getElementById("contentModal"),d=document.getElementById("statusModal"),a=document.getElementById("itemIdModal");t.value=document.getElementById("title"+e).innerHTML,n.value=document.getElementById("content"+e).innerHTML,d.value=document.getElementById("status"+e).innerHTML,a.value=document.getElementById("itemId"+e).value}function deleteItem(e){var t=document.getElementById("itemId"+e).value;$.ajax({url:"/api/item/"+t,type:"DELETE",success:function(e){$("#pageNumsList").html(""),getPageData(!0)}})}function addItem(){var e="?title="+document.getElementById("titleAddItem").value+"&content="+document.getElementById("contentAddItem").value+"&status="+document.getElementById("statusAddItem").value;$.post("/addItem"+e,function(e,t){$("#pageNumsList").html(""),getPageData(!0)})}var itemsStrArray=[],index=0;getPageData(!0),$.get("/getLoggedUserName",function(e,t){document.getElementById("userNameLabel").innerHTML="Hi, "+e}),$(document).ready(function(){$(".modal-trigger").leanModal()});