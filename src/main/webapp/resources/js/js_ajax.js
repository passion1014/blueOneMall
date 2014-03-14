/******************************************************************************
 * 함수명    : createRequest()
 * 함수내용  : 요청객체를 생성하는 함수
 * 반환값    : 요청객체
 *****************************************************************************/
function createRequest(){
	
	var request = null ;

	try
	{
		request = new XMLHttpRequest();
	}
	catch (trymicrosoft)
	{
		try
		{
			request = new ActiveXObject("Msxm12.XMLHTTP");
		}
		catch (othermicrosoft)
		{
			try
			{
				request = new ActiveXObject("Microsoft.XMLHTTP");
			}
			catch (failed)
			{
				request = null ;
			}
		}
	}

	if (request == null)
	{
		alert("Error creating request object!");
	}

	return request ;

}



 /******************************************************************************
 * 함수명    : 
 * 함수내용  : 
 * 반환값    : 
 * 사용법    : 
 *****************************************************************************/
function replaceText(el, text) {
  if (el != null) {
    clearText(el);
    var newNode = document.createTextNode(text);
    el.appendChild(newNode);
  }
}

function clearText(el) {
  if (el != null) {
    if (el.childNodes) {
      for (var i = 0; i < el.childNodes.length; i++) {
        var childNode = el.childNodes[i];
        el.removeChild(childNode);
      }
    }
  }
}

function getText(el) {
  var text = "";
  if (el != null) {
    if (el.childNodes) {
      for (var i = 0; i < el.childNodes.length; i++) {
        var childNode = el.childNodes[i];
        if (childNode.nodeValue != null) {
          text = text + childNode.nodeValue;
        }
      }
    }
  }
  return text;
}