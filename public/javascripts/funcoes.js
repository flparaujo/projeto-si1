function allowDrop(ev,e)
{
	$("#"+e.id).addClass("periodoDrag");
	ev.preventDefault();
}
function drag(ev){
	$(".periodo").addClass("periodoActive");
	ev.dataTransfer.setData("Text",ev.target.id);
}
function leave(ev, e){
	$("#"+e.id).removeClass("periodoDrag");
}
function dragEnd(){
	$(".periodo").removeClass("periodoActive");
}
function drop(ev, e){
	$(".periodo").removeClass("periodoActive");
	$("#"+e.id).removeClass("periodoDrag");
	ev.preventDefault();
	var data=ev.dataTransfer.getData("Text");
	$.ajax({
		  type: "POST",
		  url: "/addCadeira/"+data+"/"+e.id,
		  data: "",
		  success: function(){
		        alert("Disciplina Adicionada");
		        window.location = "/";
		  },
		  error: function(result, exception, v, r) {
			  if (v == "Bad Request"){
				  alert("Limite de Créditos Ultrapassados!");
			  }
		  }
		});
}

$(document).ready(function(){
	$(".alocadas").hover(function(){
		  $(this).css("opacity", "0.5")
		  },function(){
		  $(this).css("opacity", "1")
		  $(this).children("span").css("visibility", "hidden")
		});
	$(".alocadas-vermelho").hover(function(){
		  $(this).css("opacity", "0.8")
		  },function(){
		  $(this).css("opacity", "1")
		  $(this).children("span").css("visibility", "hidden")
		});

	
	});

function remDisciplina(disciplina, flagRequisitos){
	var r = true;
	if (flagRequisitos == 'true'){
		r=confirm("A remoção dessa disciplina implicará na remoção de outra(s) cadeira(s), você realmente deseja removê-la?");
	}
	if (r==true){
	$.ajax({
		  type: "POST",
		  url: "/remomerDisciplina/" + cadeira,
		  data: "",
		  success: function(){
		        alert( "Disciplina Removida");
		        window.location = "/";
		  },
		  error: function(XMLHttpRequest, textStatus, errorThrown) {
			  var ind = XMLHttpRequest.responseText.indexOf("Exception:");
			  var res = XMLHttpRequest.responseText.substring(ind +11 , ind + 500);
			  var ind2 = res.indexOf("]");
			  var res2 = res.substring(0, ind2);
		      alert(res2);
		  }
		});
	}
}
