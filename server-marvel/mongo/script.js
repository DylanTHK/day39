use marvel;

// inserting values
db.comments.insertMany([
    {ts: '2023-04-01 14:35:28.843888', id: 1, comment: "Sample comment1"},
    {ts: '2023-04-01 15:35:28.843888', id: 2, comment: "Sample comment2"},
    {ts: '2023-04-01 16:35:28.843888', id: 3, comment: "Sample comment3"},
    {ts: '2023-04-01 17:35:28.843888', id: 4, comment: "Sample comment4"},
    {ts: '2023-04-01 18:35:28.843888', id: 5, comment: "Sample comment5"},
    {ts: '2023-04-01 19:35:28.843888', id: 6, comment: "Sample comment6"},
    {ts: '2023-04-01 20:35:28.843888', id: 7, comment: "Sample comment7"},
    {ts: '2023-04-01 21:35:28.843888', id: 8, comment: "Sample comment8"},
    {ts: '2023-04-01 22:35:28.843888', id: 9, comment: "Sample comment9"},
    {ts: '2023-04-01 23:35:28.843888', id: 10, comment: "Sample comment10"},
    {ts: '2023-04-01 23:50:28.843888', id: 11, comment: "Sample comment11"}
]);

// sample command to save one entry
db.comments.insert(
    {ts: 12, id: 12, comment: "Sample comment12"}
)

// retrieve top 10 comments (sort DESC, limit 10)
db.comments.aggregate([
    {
        $match: {id: 1017603}
    }
    ,
    {
        $sort: {ts: -1}
    }
    ,
    {
        $limit: 10
    }
]);

db.comments.find();
