(ns archibald-two.db
  (:use [korma.db]
        [korma.core]))

(defdb db (postgres {:db "archibald"
                     :user "archibald"
                     :password "regin4ld"})) ; should probably refer to env var here

(defentity posts)

(defn all-posts
  []
  (into [] (select posts)))
