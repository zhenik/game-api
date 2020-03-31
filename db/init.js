db = db.getSiblingDB("app")

db.createUser({
  user: "myUser",
  pwd: "myPassword",
  roles: [{role: "readWrite", db: "app"}]
})

db.trash.insert({ myfield: 'hello2', thatfield: 'testing' })
