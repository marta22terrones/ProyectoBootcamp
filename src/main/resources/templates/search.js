var handleSearch = function (event) {
    event.preventDefault();
    // Get the search terms from the input field
    var searchTerm = event.target.elements['search'].value;
    // Tokenize the search terms and remove empty spaces
    var tokens = searchTerm
        .toLowerCase()
        .split(' ')
        .filter(function (token) {
            return token.trim() !== '';
        });
    if (tokens.length) {
        //  Create a regular expression of all the search terms
        var searchTermRegex = new RegExp(tokens.join('|'), 'gim');
        var filteredList = films.filter(function (film) {
            // Create a string of all object values
            var filmString = '';
            for (var key in film) {
                if (film.hasOwnProperty(key) && film[key] !== '') {
                    filmString += film[key].toString().toLowerCase().trim() + ' ';
                }
            }
            // Return book objects where a match with the search regex if found
            return filmString.match(searchTermRegex);
        });
        // Render the search results
        return filteredList;
    }
};