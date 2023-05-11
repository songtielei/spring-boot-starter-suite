## dozer 支持 jdk8 date
```
    private static DozerBeanMapper dozer = new DozerBeanMapper();

    static {
        List<String> mappingFileUrls = new ArrayList<>(1);
        mappingFileUrls.add("dozer-custom-convert.xml");
        dozer.setMappingFiles(mappingFileUrls);
    }
```

resources
  dozer-custom-convert.xml