(ns archibald-two.db
  (:use [korma.db]
        [korma.core]))

(defdb db (postgres {:db "archibald"
                     :user "archibald"
                     :password "regin4ld"})) ; should probably refer to env var here

(defentity posts)
(defentity quotes)

(defn all-posts
  []
  (into [] (select posts (order :date_updated :DESC))))

(defn all-quotes
  []
  (into [] (select quotes (order :date_updated :DESC))))

(defn add-post-to-db
  [title content tags]
  (insert posts
          (values {:title title :content content :tags tags})))

(defn add-quote-to-db
  [the_quote author]
  (insert quotes
          (values {:quote the_quote :author author})))
  
