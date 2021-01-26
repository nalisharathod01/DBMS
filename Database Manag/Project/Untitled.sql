SELECT * FROM sampledb.Blogs;
SELECT * FROM sampledb.Comments;

drop view one;
drop view two;

Create view one as 
select t1.blogId , postuser, author,sentiment
from Blogs t1 , Comments T2
where t1.blogId=t2.blogId;

Create view two as 
select t1.blogId,t1.postuser , t1.author,t1.sentiment
from one t1,one t2
where t1.blogId<>t2.blogId 
and t1.postuser=t2.author and t2.postuser=t1.author;

select * from two;

select t1.blogId,t1.postuser , t1.author ,t1.sentiment f
from two t1
where t1.postuser<t1.author and
(t1.postuser , t1.author)
not in
(
	select t3.postuser , t3.author from two t3
    where sentiment="negative"
    and t3.postUser=t1.postuser and t3.author=t1.author
);






