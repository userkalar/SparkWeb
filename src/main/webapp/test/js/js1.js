var bgImg = new Image();
bgImg.src = "https://picsum.photos/800/600?random=1";
bgImg.onload = function() {
  document.getElementById("bg").style.backgroundImage = "url('" + bgImg.src + "')";
}