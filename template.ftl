FreeMarker Template example: ${message}

=======================
===  County List   ====
=======================
${masker.handler}
<#list countries as country>
	${country_index + 1}. ${country}
</#list>
