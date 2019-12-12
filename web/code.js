/* jshint esversion: 6 */

/**
 * Search by clicking enter key
 */
var input = document.getElementById("searchInput");
input.addEventListener("keyup", function(event) {
    if (event.keyCode === 13) {
        event.preventDefault();
        document.getElementById("searchButton").click();
    }
});

/**
 * Search by clicking enter key
 */
var container = document.getElementById("searchButton");
container.addEventListener('click', function (event) {
    var ball = document.querySelector(".searchBox");
    ball.style.position = "fixed";
    ball.style.left = `75%`;
    ball.style.top = `45%`;
    ball.style.transition = `0.25s ease-in`;
    head.style.left = `75%`
})

/**
 * Getting and formatting search results
 */
document.getElementById('searchButton').onclick = () => {
    fetch("/search?q=" + document.getElementById('searchInput').value)
    .then((response) => response.json())
    .then((data) => { 
        if(data.length == 0)    {
            document.getElementById("responsesize").innerHTML = 
            "<p> No website contains the query word </p>";
        } else { 
        document.getElementById("responsesize").innerHTML = 
        "<p>" + data.length + " websites retrieved</p> \n <p> – CURRENT SEARCH –</p> \n <p>" + document.getElementById('searchInput').value + "</p>";
        }
    let results = data.map((page) =>
        `<li><h2><a href="${page.url}">${page.title}</h2></a>
        <p>${page.url}</p>\n<p> Words: ${page.totalWords}</p><p>${page.words}</p>\n<p><i> Page Relevance: ${page.relevance}</i></p></li>`)
        .join("\n");
        
        document.getElementById("urllist").innerHTML =`<ol>${results}</ol>`;
        window.scrollTo({ top: 0, behavior: 'smooth' });
       
    });
};
