## RULE34 SAVER

AutoSaving content from rule34 by provided search list.

Before saving, it self-checks is the same filename/directory already exists, this makes possible to run without duplicates. 

```application.yml```
* __Settings:__
    * __threads:__ _depends on performance of the machine where this application is running, default 2._
    * __saveFolder:__  _folder where content will be saved and sorted by search word._
    * __searchList:__ _each search in new line, can be combined as demonstrated, can exclude tags from search result by "+-"._

```run Application.class```

__Why this order was born?__ <br>
Because of the restrictions of content, it may be deleted before you will see it. But it is deleted by hands, after it was published.
Because of this, application can download new content after it was published and before it was deleted.

_P.S. May have timeout, but don't worry, it's short._
