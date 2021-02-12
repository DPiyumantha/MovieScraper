<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>MovieScraper</title>
</head>
<body>
<div style="margin-left: auto; text-align: center;"></div>
Hi, ${name}
<h1>Just for you</h1>
<#list filteredMovies>

    <#items as item>
        <h2> <a href="${item.link}">${item.name} </a> </h2></br>
    </#items>
    Enjoy!</br>
<#else></br>
    No movies according to your filters. :( but...</br>
</#list>

</br>
<h1>All the movies we scraped</h1>
<#list allMovies>

    <#items as item>
        <h2> <a href="${item.link}">${item.name} </a> </h2></br>
    </#items>
    </br>
    Happy movie time!
<#else>

</#list>
</body>
</html>