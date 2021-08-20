console.log("Running javascript")

$(document).ready(function(){
        // Initializes the carousel
            $(".start-slide").click(function(){
            	$("#myCarousel").carousel('cycle');
            });
        // Stops the carousel
            $(".pause-slide").click(function(){
            	$("#myCarousel").carousel('pause');
            });

        $(".carousel-control-prev").click(function(){
                $("#myCarousel").carousel('prev');
        });
        $(".carousel-control-next").click(function(){
                $("#myCarousel").carousel('next');
        });

        // Cycles the carousel to a particular frame
            $(".slide-one").click(function(){
            	$("#myCarousel").carousel(0);
            });
            $(".slide-two").click(function(){
            	$("#myCarousel").carousel(1);
            });
            $(".slide-three").click(function(){
            	$("#myCarousel").carousel(2);
            });
});