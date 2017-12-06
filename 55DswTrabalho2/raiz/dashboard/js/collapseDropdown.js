$(document).ready(function(){
    $('#side-menu li > ul').on('click', addEventsSideMenu());
});

function addRemoveCollapse(obj) {
    if(obj == this.currentTarget){
        if(obj.className.search('collapse') !== -1){
           obj.className = obj.className.replace('collapse', ' ');
        } else {
           obj.className += 'collapse';
        }
    }
}

function addEventsSideMenu() {
      var eUls = $('#side-menu li > ul');
        for(var i = 0; i <= eUls.length -1; i++){
            var eUl = eUls[i];
            eUl.addEventListener('click', addRemoveCollapse(eUl), false);
        }
}
