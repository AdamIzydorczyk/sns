{
query: [ 
{field: "fieldInBase", values: ["foo", "bar","foobar"]}, // where fieldInBase in ['foo', 'bar','foobar'] 
{field: "related.fieldInRelated", mode: SORT}, // join e.related j1 order by j1.fieldInRelated   
{field: "related.fieldInRelated", value: "foo", mode: NOT}, // join e.related j1 where j1.fieldInRelated != 'foo'    
{field: "fieldInBase", value: "foo", mode: LIKE_STARTED}, // where fieldInBase LIKE 'foo%'   
{field: "related.relatedToRelated.fieldInRelated", value: "foo", mode: LIKE_ENDED}, // join e.related j1 join j1.relatedToRelated j2 where j2.fieldInRelated LIKE '%foo' 
{field: "fieldInBase", value: "foo", mode: LIKE_CONTAIN}, // where e.fieldInBase LIKE '%foo%'    
{field: "fieldInBase", mode: NOT_NULL}, // where e.fieldInBase LIKE '%foo%' is not null  
{field: "fieldInBase", mode: IS_NULL}] // where e.fieldInBase LIKE '%foo%' is null   
page:   
{pageNumber : 0, size: 1}
}
