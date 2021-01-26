SELECT * FROM sampledb.tb_user;
SELECT * FROM sampledb.Hobby;
SELECT * FROM sampledb.Comment;
SELECT * FROM sampledb.TagList;
SELECT * FROM sampledb.UserFollwers;
SELECT * FROM sampledb.Blog;
SELECT * FROM sampledb.PostBlog;
SELECT * FROM sampledb.PostComment;


select username from postBlog pb where count(pb.username) in(
select count(*) from PostComment pc Group By pc.username,pc.blogId );


Select * from tb_user where 2<(
select username from PostComment Group By  username,date);


select username from PostBlog where 6 in(
select username from PostBlog Group By  username,date);



Select * from PostComment pc where pc.username Not In ( 
Select count(*) from PostBlog pb where pb.username=pc.username and pb.blogId=pc.blogId);


select username from PostComment  Group By  username,date having count(*)>2;

select username from PostBlog  Group By  username,date having count(*)>5;

select username,count(*) count, blogId from PostComment Group By username,blogId having count(*)>1;
