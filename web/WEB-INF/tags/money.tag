<%@tag description="Show R$ in input money" pageEncoding="UTF-8"%>

<%@attribute name="id"%>
<%@attribute name="classe"%>
<%@attribute name="nome"%>
<%@attribute name="valor"%>

<input class="${classe}" type="text" id="${id}" 
       name="${nome}" required value="${valor}"/>

<script src="resources/js/jquery.mask.min.js"></script>
<script src="resources/js/jquery.maskMoney.min.js"></script>

<script>
    window.addEventListener("load", function(){
        var el = document.getElementById("${id}");
        var str = el.value.replace(".", ",");
        el.value = str;
    });
    
    $(document).ready(function(){
        $("#${id}").maskMoney({
            prefix: "R$ ",
            decimal: ",",
            thousands: "."
        });
    }); 
</script>
