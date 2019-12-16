/* jshint esversion: 6 */

var input = document.getElementById("searchbox");
input.addEventListener("keyup", function(event) {
    if (event.keyCode === 13) {
        event.preventDefault();
        document.getElementById("searchbutton").click();
    }
});

document.getElementById('searchbutton').onclick = () => {
    fetch("/search?q=" + document.getElementById('searchbox').value)
    .then((response) => response.json())
    .then((data) => {
        if(data.length == 0)    {
        document.getElementById("responsesize").innerHTML = 
        "<p> No website contains the query word </p>";
    } else { 
    document.getElementById("responsesize").innerHTML = 
            "<p>" + data.length + " websites retrieved</p>";
    }
        let results = data.map((page) =>
            `<li><a href="${page.url}">${page.title}</a></li> 
             <p>${page.url}</p>\n<p> Words: ${page.totalWords}</p><p>${page.words}</p>\n<p><i> Page Relevance: ${page.relevance}</i></p></li>`)
            .join("\n");
        document.getElementById("urllist").innerHTML = `<ul>${results}</ul>`;
    });
};
    