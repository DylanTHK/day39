use marvel;

db.comments.insertMany([
    {ts: 1, id: 1, comment: "Sample comment1"},
    {ts: 2, id: 2, comment: "Sample comment2"},
    {ts: 3, id: 3, comment: "Sample comment3"},
    {ts: 4, id: 4, comment: "Sample comment4"},
    {ts: 5, id: 5, comment: "Sample comment5"},
    {ts: 6, id: 6, comment: "Sample comment6"},
    {ts: 7, id: 7, comment: "Sample comment7"},
    {ts: 8, id: 8, comment: "Sample comment8"},
    {ts: 9, id: 9, comment: "Sample comment9"},
    {ts: 10, id: 10, comment: "Sample comment10"},
    {ts: 11, id: 11, comment: "Sample comment11"}
]);

//sort by desc
db.comments.aggregate([
    {$sort: {ts: -1}},
    {$limit: 10}
]);