let colors = ['red', 'orange', 'yellow', 'green', 'blue', 'indigo', 'violet'];  
let index = 0;  
  
setInterval(function() {  
    let poem = document.getElementById('poem');  
    poem.style.color = colors[index % colors.length];  
    index++;  
}, 1000);  // 每秒改变一次颜色