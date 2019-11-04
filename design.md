# Project Design
### Reading Data
Due to the potential enormous size of the data file (dblp_papers_v11.txt), the number of data instances read is limited to a specified number.
The other method, not presented in this project, would be to read the file over again when searching for references. However, the program performance is reduced dramatically so the prior is chosen.
### Data Filter
By utilizing Java streams, required functionalities becomes very easy and efficient to implement. This is pressented through data collection and filtering, as well as sorting and output.
### Configurations
* Searching for keywords to match and test on.
* Choosing different input files.
* Max number of instances to read and test on.
* Max depth for reference tree.
