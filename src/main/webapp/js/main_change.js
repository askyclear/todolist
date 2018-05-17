/**
 * main html에서 버튼을 누르면 다음 상태로 변경해주는 script ajax를 활용하여 변화하는 부분만
 * 재로드;
 */
//ajax callback method
function callBackAjax(oReq, id, type){
	if(oReq.readyState == 4 && oReq.status == 200){
		//응답받은 jsonString을 json Object로 바꿔줌
		var jsonObj = JSON.parse(oReq.responseText);
		//json에서 바뀌어야할 list 찾기
		for(var i in jsonObj){
			//jsonObject에서 현재 클릭한 id의 값과 같은 jsonObj을 찾음
			
			if(jsonObj[i].id == id){
				//업데이트된 jsonObj을 통해 list element를 만들어줌
				var ele= jsonToElement(jsonObj[i]);
				//기존의 list element를 삭제해주고
				var eleRemoveParent = document.getElementById(type.toLowerCase());
				eleRemoveParent.removeChild(document.getElementById(type + id));
				//새로만든 list element를 업데이트 되야할 list에 추가함;
				var eleAddParent = document.getElementById(jsonObj[i].type.toLowerCase());
				eleAddParent.appendChild(ele);
			}
		}	
	}
}
//click callback
function clickCallBack(){
	var oReq = new XMLHttpRequest();
	var attrName = this.getAttribute('name');
	//name에서 type 와 id 값 추출
	var splitedName = attrName.split("_");
	var id = splitedName[1];
	var type = splitedName[0];
	//업데이트 요청하기
	oReq.open("GET","./todochange?id=" + id +"&type=" + type);
	//성공시 callback function
	oReq.addEventListener("load",function(){
		callBackAjax(oReq, id, type);
	});
	oReq.send();
}
//json to element
function jsonToElement(jsonObj){
	var ele = null;
	ele = document.createElement("div");
	ele.setAttribute("class","list");
	ele.setAttribute("id",jsonObj.type+jsonObj.id);
	var ele2 = document.createElement("h2");
	var ele2_textNode = document.createTextNode(jsonObj.title);
	ele2.appendChild(ele2_textNode);
	var ele3 = document.createElement("div");
	var ele3_textNode = document.createTextNode("등록날짜:"+jsonObj.regdate +", " + jsonObj.name +", 우선순위" + jsonObj.sequence);
	ele3.appendChild(ele3_textNode);
	if(jsonObj.type!="DONE"){
		var ele4 = document.createElement("input");
		ele4.addEventListener("click",clickCallBack);
		ele4.setAttribute("class","type_change");
		ele4.setAttribute("name",jsonObj.type + "_" + jsonObj.id);
		ele4.setAttribute("type","button");
		ele4.setAttribute("value",">");
		ele3.appendChild(ele4);
	}
	ele.appendChild(ele2);
	ele.appendChild(ele3);
	return ele;
}

//add click event listener
var elements = document.querySelectorAll('.type_change');
for(var i=0; i < elements.length; i++){
	elements[i].addEventListener("click",clickCallBack);
}
