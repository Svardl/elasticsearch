# Report for assignment 4

## Project

Name: Elasticsearch

URL: https://github.com/elastic/elasticsearch

Elasticsearch is a distributed RESTful search engine built for the cloud

## Complexity

1. What are your results for the ten most complex functions? (If ranking
is not easily possible: ten complex functions)?
    
   
    
   * Did all tools/methods get the same result?
   * Are the results clear?
2. Are the functions just complex, or also long?
3. What is the purpose of the functions?

    readHost 
    It reads tokens until there are none left. If a token is a field name  
    a variable is set to that that name. if instead the token is a start 
    object the bulk of the program is run. if it's neither of these things 
    the next token is selected. in the case of a start object if checks wheter
    or not the field name variable has been set to "http" a new token is read 
    while the read tokens are not end objects the httpHost is read, if a start
    object is found it skips its children.
    
    Lastly if nothing could be read that is logged and null is returned. if it 
    did read something that is returned. 
    readHosts
  
  
4. Are exceptions taken into account in the given measurements?
5. Is the documentation clear w.r.t. all the possible outcomes?

## Coverage

### Tools

Document your experience in using a "new"/different coverage tool.

How well was the tool documented? Was it possible/easy/difficult to
integrate it with your build environment?

### DYI

Show a patch that show the instrumented code in main (or the unit
test setup), and the ten methods where branch coverage is measured.

The patch is probably too long to be copied here, so please add
the git command that is used to obtain the patch instead:

git diff ...

What kinds of constructs does your tool support, and how accurate is
its output?

### Evaluation

Report of old coverage: [link]

Report of new coverage: [link]

Test cases added:

git diff ...

## Refactoring

Plan for refactoring complex code:

Carried out refactoring (optional)

git diff ...

## Effort spent

For each team member, how much time was spent in

1. plenary discussions/meetings;

2. discussions within parts of the group;

3. reading documentation;

4. configuration;

5. analyzing code/output;

6. writing documentation;

7. writing code;

8. running code?

## Overall experience

What are your main take-aways from this project? What did you learn?

Is there something special you want to mention here?
