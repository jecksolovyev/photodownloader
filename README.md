App allows to download images from the Internet to local drive, renaming files as needed.

# TSV list
 TSV file, must have at least two columns: `Url` and `RenameTo`
```
#	S3URI	RenameTo
1	aa8edcd1-8342-3fbb-3846-dc5d630c34cd.jpg	4607062866757/349653_2.jpg
2	aa8edcd1-8342-3fbb-3846-dc5d630c34cd.6.jpg	4607062866757/349653_13.jpg
```

# Config
Jar must be in the same folder together with `config.properties`. 

Config must have following params:
* tsvFileName - Path to TSV file, must have at least two columns: `Url` and `RenameTo`
* urlColumnIndex - `Url` column index in TSV file, integer
* renameToColumnIndex - `RenameTo` column index in TSV file, integer
* s3UriPrefix - Url prefix, will be used as `s3UriPrefix` + `Url` to download
* downloadToFolder - Destination folder, will be used as `downloadToFolder` + `RenameTo` to store downloaded file locally

# Run
To run `java -jar photodownloader.jar`
