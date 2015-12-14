# SIGMAC #
This project is aimed at developing a system to generate concept maps for document repositories to effective conceptual level browsing and document summaries. For more information visit [SigmaC](http://sigmac.sldigits.com)

## How It Works ##
Following list contains the steps for single document:
  * Each document is processed to extract titles and text body separately using document type specific adapter ---
  * Text body is parsed using Stanford parser to create phrase structure tree and set of patterns are matched against the tree to identify concepts and relationships.
  * The concepts identified by patterns are specified to the relationship being described by the sentence.
  * Set of titles, concept and relationship frequencies and Wordnet is used to calculate strength of each concept and relationship.
  * Concepts of each document is ranked using Page Rank algorithm