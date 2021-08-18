console.log("Running javascript")

$("#btn-services").hover(function(){//not done yet
    $(this).find(".dropdown-menu").stop(true,true).delay(100).fadeIn(500);
},
function(){
    $(this).find(".dropdown-menu").stop(true,true).delay(100).fadeOut(500);
}
)