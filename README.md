
# Markdown to HTML converter

Converts Markdown file to HTML format

## Description

The functionality includes subset of markdown formats. 

| Markdown                               | HTML                                              |
| -------------------------------------- | ------------------------------------------------- |
| `# Heading 1`                          | `<h1>Heading 1</h1>`                              |
| `## Heading 2`                         | `<h2>Heading 2</h2>`                              |
| `...`                                  | `...`                                             |
| `###### Heading 6`                     | `<h6>Heading 6</h6>`                              |
| `Unformatted text`                     | `<p>Unformatted text</p>`                         |
| `[Link text](https://www.example.com)` | `<a href="https://www.example.com">Link text</a>` |
| `Blank line`                           | `Ignored`                                         |


# Prerequisites

 Java 17, Gradle 7.6

## Usage 
  
from the `mc-craft-demo-solution` directory, run the below commands to build and execute the code. 
1. Remove previous build files`./gradlew clean`
2. Build the code : `./gradlew build`
3. Build the fat jar: `./gradlew jar`
4. To execute the code, run the following command to replace the input and output file name with the absolute path of the files:
    ` java -jar ./app/build/libs/app.jar <absolute path of input file> <<absolute path of output file>`

*Example command to execute to generate the HTML document from the markdown file*

`java -jar ./app/build/libs/app.jar /Users/johndoe/Documents/junk/mc-input/test6.md /Users/johndoe/Documents/junk/output.html`

##### Test coverage generation report, 

To generate the test coverage report, 

from the `mc-craft-demo-solution` directory, run the below commands,

 `./gradlew jacocoTestReport`

Above command will generate test coverage report at the following path: 
`mc-craft-demo-solution/app/build/reports/jacoco/test/html/index.html`


