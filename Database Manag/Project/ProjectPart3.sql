
CREATE VIEW three AS
SELECT b.blogId, b.subject, b.description, b.postUser,b.postDate,bt.tags
FROM Blogs b, BlogTags bt
WHERE b.blogId=bt.blogId
AND bt.tags IN ('tag1','tag2');


SELECT DISTINCT t1.postuser FROM BlogWithtags t1, BlogWithtags t2 
WHERE t1.blogId<>t2.blogId AND t1.postUser=t2.postUser 
AND t1.tags <> t2.tags;


-- 2th query

SELECT postUser
FROM Blogs 
where blogId in(
Select blogId from BlogTags
where tags="tag3" and tags="tag2") GROUP BY postUser
having count(blogId)>=2;


-- 3nd query

Select blogId, subject from Blogs where postUser="Shweta" and blogId in(
Select blogId from Comments
where sentiment="Positive" and blogId not in 
(Select blogId from Comments
where sentiment="Negative"));

-- 4th query

SELECT postUser FROM sampledb.Blogs
where postDate="2018-04-05"
GROUP BY postUser
having count(*)=(
SELECT MAX(num) FROM
(SELECT postUser, COUNT(*) as num
		 	FROM Blogs 
            where postDate="2018-04-05"
			GROUP BY postUser
        		 	)AS maxBlogs
);

-- 5th query

Select T1.leader from Followers T1 , Followers T2
where T1.leader=t2.leader and T1.follower="Shweta"  and T2.Follower="Swapnil";

-- 6th query

select username from tb_user 
where username 
not in
(select postUser from Blogs);

-- 7th query
select username from tb_user 
where username 
not in
(select author from Comments);

-- 8th query

select distinct author from Comments where sentiment="Negative" 
and author not in (select author from Comments where sentiment="Positive");

-- 9th query

Drop view userTable;
Drop view user_negativeonly;

Create View userTable as
select B.blogId,postUser,author,sentiment
from Blogs B, Comments C
where B.blogId=C.blogId;

Create View user_negativeonly as
select distinct t1.blogId ,t1.postUser 
from userTable t1
where t1.sentiment="negative";

Select distinct b1.postUser from Blogs b1
where b1.postUser
not in( select postUser from user_negativeonly);


-- 10th query

drop view authors;
drop view positiveOnly;
drop view duplicateListOfuser;

Create view authors as 
select  distinct t2.blogId , t1.postuser, t2.author,t2.sentiment
from Blogs t1, Comments T2
where t1.blogId=t2.blogId;

Create view positiveOnly as 
select t1.author,t1.postuser
from authors t1
where not exists( select t2.author,t2.postuser from authors t2
where t1.author=t2.author
and t1.postuser=t2.postuser
and t2.sentiment="negative");

Create view duplicateListOfuser as 
select a1.author,a1.postuser from positiveOnly a1
where exists
(select * from positiveOnly a2 where a1.author=a2.postuser and a1.postuser=a2.author);

select postUser, author from duplicateListOfuser
where postUser < author;
