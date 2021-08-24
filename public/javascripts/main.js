$(document).ready(function(){
    //Add smooth scrolling to all links
    $(".nav-link").on("click",function(event){
        //make sure this.hash has value before overriding default behavior
        if(this.hash != ""){
            //prevent default clicking
            event.preventDefault();
            //store hash
            var hash = this.hash;

            //using jquery's animate() method to add smooth page scroll
            //The optional number (800) specifies the number of milliseconds it takes to scroll to the specified area
            $("html,body").animate({
                scrollTop: $(hash).offset().top
            },800,function(){
                // Add hash (#) to URL when done scrolling (default click behavior)
                window.location.hash = hash;
            });
        }// End 'if' condition
    });

    //prevent default clicking on pages
    $(".social-link").click(function(e){
        e.preventDefault();
    })
});