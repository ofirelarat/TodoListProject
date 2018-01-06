function getPageData(e){index=0,$.get("/items",function(t,n){itemsStrArray=t.split("-"),renderData1(),renderData2(),renderData3(),1==e&&renderPageNumbersList()})}function renderPageNumbersList(){var e,t=itemsStrArray.length/3;itemsStrArray.length%3!=0&&t++;var n=document.getElementById("pageNumsList"),a=document.createElement("LI");a.setAttribute("class","waves-effect");var d=document.createElement("a");d.setAttribute("href","javascript:prevPage()");var i=document.createElement("I");for(i.setAttribute("class","material-icons"),i.innerHTML="chevron_left",d.appendChild(i),a.appendChild(d),n.appendChild(a),e=1;t>=e;e++){var m=document.createElement("LI");m.setAttribute("id","page"+e),1==e&&m.setAttribute("class","active");var d=document.createElement("A");d.innerHTML=e,m.appendChild(d),n.appendChild(m)}var r=document.createElement("LI");r.setAttribute("class","waves-effect");var d=document.createElement("a");d.setAttribute("href","javascript:nextPage()");var i=document.createElement("I");i.setAttribute("class","material-icons"),i.innerHTML="chevron_right",d.appendChild(i),r.appendChild(d),n.appendChild(r)}function renderData1(){if(index<itemsStrArray.length){var e=itemsStrArray[index];if(""!=e){var t=e.split(","),n=t[0],a=t[1],d=t[2],i=t[3];document.getElementById("item1").style.display="block",document.getElementById("title1").innerHTML=a,document.getElementById("content1").innerHTML=d,document.getElementById("status1").innerHTML=i,document.getElementById("itemId1").value=n,index++}}else document.getElementById("item1").style.display="none"}function renderData2(){if(index<itemsStrArray.length){var e=itemsStrArray[index];if(""!=e){var t=e.split(","),n=t[0],a=t[1],d=t[2],i=t[3];document.getElementById("item2").style.display="block",document.getElementById("title2").innerHTML=a,document.getElementById("content2").innerHTML=d,document.getElementById("status2").innerHTML=i,document.getElementById("itemId2").value=n,index++}}else document.getElementById("item2").style.display="none"}function renderData3(){if(index<itemsStrArray.length){var e=itemsStrArray[index];if(""!=e){var t=e.split(","),n=t[0],a=t[1],d=t[2],i=t[3];document.getElementById("item3").style.display="block",document.getElementById("title3").innerHTML=a,document.getElementById("content3").innerHTML=d,document.getElementById("status3").innerHTML=i,document.getElementById("itemId3").value=n,index++}}else document.getElementById("item3").style.display="none"}function nextPage(){index<itemsStrArray.length&&($("#page"+index/3).removeClass("active"),$("#page"+(index+3)/3).addClass("active"),renderData1(),renderData2(),renderData3())}function prevPage(){index>3&&(index%3==0?index-=6:index%3==2?index-=5:index%3==1&&(index-=4),renderData1(),renderData2(),renderData3(),$("#page"+(index+3)/3).removeClass("active"),$("#page"+index/3).addClass("active"))}function editItem(){var e=document.getElementById("titleModal").value,t=document.getElementById("contentModal").value,n=document.getElementById("statusModal").value,a=document.getElementById("itemIdModal").value,d="?title="+e+"&content="+t+"&status="+n+"&id="+a;$.post("editItem"+d,function(e,t){1==e&&(swal("Update Data!","Your data item has updated.","success"),getPageData(!1))})}function editItemDialog(e){var t=document.getElementById("titleModal"),n=document.getElementById("contentModal"),a=document.getElementById("statusModal"),d=document.getElementById("itemIdModal");t.value=document.getElementById("title"+e).innerHTML,n.value=document.getElementById("content"+e).innerHTML,a.value=document.getElementById("status"+e).innerHTML,d.value=document.getElementById("itemId"+e).value}function deleteItem(e){var t=document.getElementById("itemId"+e).value;$.ajax({url:"/api/item/"+t,type:"DELETE",success:function(e){$("#pageNumsList").html(""),swal("Delete Data!","Your data item has deleted.","success"),getPageData(!0)}})}function addItem(){var e=document.getElementById("titleAddItem").value,t=document.getElementById("contentAddItem").value,n=document.getElementById("statusAddItem").value,a="?title="+e+"&content="+t+"&status="+n;$.post("/addItem"+a,function(e,t){$("#pageNumsList").html(""),swal("Insert Data!","Your data item has inserted.","success"),getPageData(!0)})}var itemsStrArray=[],index=0;getPageData(!0),$.get("/getLoggedUserName",function(e,t){document.getElementById("userNameLabel").innerHTML="Hi, "+e}),$(document).ready(function(){$(".modal-trigger").leanModal()});