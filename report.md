# **Report for assignment 4**
## **Project**

Name: Elasticsearch
URL: [https://github.com/elastic/elasticsearch](https://github.com/elastic/elasticsearch)
Elasticsearch is a distributed RESTful search engine built for the cloud

## **Complexity**

**What are your results for the ten most complex functions? (If ranking is not easily possible: ten complex functions)?**

| Function           | CCN |
| ------------------ | --- |
| readHost           | 11  |
| readHosts          | 8   |
| merge              | 15  |
| performRequest     | 6   |
| buildTraceResponse | 8   |
| buildTraceRequest  | 4   |
| writeTo            | 11  |
| SearchSortValues   | 12  |

**Are the functions just complex, or also long?**
Higher CCN seem to make the functions longer in general. Some of them are simply long if-else statements that lead to large cyclical complexity.

### What is the purpose of the functions?
**readHost**: It reads tokens in a .json file until there are none left. If a token is a field name, a variable is set to that that name. if instead the token is a start object the bulk of the program is run. if it's neither of these things the next token is selected. in the case of a start object if checks whether or not the field name variable has been set to "http" a new token is read while the read tokens are not end objects the httpHost is read, if a start object is found it skips its children. Lastly if nothing could be read, that is logged and null is returned. if it did read something that is returned. 

**readHosts**: Tries to make an inputstream, if that fails and exception is caught and null is returned. If it succeeds it starts looking at .json tokens. If the first token. Isn’t a start object it throws an exception. If the first token is a start object it starts reading tokens until an end object is encountered. When that happens It returns what it has read. When it’s reading it makes calls to readHost to iterative .

**buildTraceRequest**:  Creates a curl output for a given request object from which it takes the 
parameter values needed to complete the curl output, for example the URI
and the query being sent.

**buildTraceResponse**: Creates a curl output for the given response object from which it gets the
parameter values needed to complete the output, like the URI and if it was
successful or not.

**merge**: Returns a new CollapseTopDocs, containing the specified amount of collapsed results across the provided CollapseTopDocs, sorting by score. The function is based on a queue and sorts the collapsed results using selection sort.

**performRequest**: creates a request and a response object and then sends the request as a response. With various function calls as parameters. And it also throws exceptions.

**SearchSortComplexity**: The purpose of this function is to handle input streams in the context of query sorting. The cyclical complexity is caused by many possible types of inputs (e.g. String, Int, Long…).

**writeTo**: the purpose of this function is to handle output streams in the context of query sorting. The cyclical complexity is caused by many possible types of outputs (e.g. String, Int, Long…).



### Are exceptions taken into account in the given measurements?
Yes, they are taken into account.

### Is the documentation clear w.r.t. all the possible outcomes?
No, very little commenting w.r.t all the possible outcomes.



### Coverage Evaluation

| Function           | Old coverage | New coverage             |
| ------------------ | ------------ | ------------------------ |
| readHost           | 82%          | 90%                      |
| readHosts          | 91%          | 100%                     |
| buildTraceRequest  | 50%          | 75%                      |
| buildTraceResponse | 82%          | 100%                     |
| SearchSortValues   | 80%          | 90% (possible dead code) |
| writeTo            | 81%          | 92% (possible dead code) |
| merge              |              |                          |
|                    |              |                          |


### Test cases added:

| Function                                          | TestCase                                                     |
| ------------------------------------------------- | ------------------------------------------------------------ |
| readHost                                          | assertEquals( ElasticsearchHostsSniffer.readHost("Test", parser, scheme), null); |
| readHosts                                         | assertEquals (sniffer.readHosts(null), null);                |
| testTraceRequest                                  | assertThat(body, equalTo(requestBody));                      |
| testTraceResponse                                 | assertThat(body, equalTo(responseBody));                     |
| createTestItem(), used in: testFromXContent() and | *new test case data:* valueSuppliers.add(() -> 'a');         |
| createTestItem(), used in:testToXContent()        | *new test case data:* valueSuppliers.add(() -> 'a');         |
|                                                   |                                                              |
|                                                   |                                                              |
## **Refactoring**

### Plan for refactoring complex code:

**buildTraceRequest**
Here I would make use of a template request message and not a string builder. To refactor it properly I would like to rewrite some of the functions being from which the function is being called so that the information being sent might be better formated. But overall rather well written code.

**buildTraceResponse**
I would have a template response message in which I would substitute the information relevant for this specific response, much of the code is new lines and inserting breaks between lines. A lot of the code is duplicated between the functions in this file, so I would have made help functions that would be used instead.

**SearchSortValues(StreamInput in)**
I would use a hashmap that maps type integers (from 1-8) and their cooresponding type functions (e.g. in.readInt() or in.readByte()). This would reduce the number of nodes by 8, and number of edges by 16, thus reducing the complexity significantly. This would increase coverage by mitigating deadcode

**writeTo(StreamOutput out)**
like the SearchSortValues I would use a hash map to simplify the large if-else block. This would reduce the number of conditionals / edges significantly, removing about 7 nodes and 14 edges. This would also probably increase coverage by mitigating deadcode.




