(ns archibald-two.handler
  (:use compojure.core)
  (:require [archibald-two.application]
            [compojure.handler :as handler]
            [compojure.route :as route]))

(defroutes app-routes
           (GET "/" [] (archibald-two.application/index))
           (GET "/add-post" [] (archibald-two.application/add-post))
           (POST "/add-post" {params :params} (archibald-two.application/post-added params))
           (GET "/posts" [] (archibald-two.application/posts))
           (GET "/quotes" [] (archibald-two.application/quotes))
           (GET "/about" [] (archibald-two.application/about))
           (GET "/contact" [] (archibald-two.application/contact))
           (route/resources "/")
           (route/not-found "Not Found"))

(def app
  (handler/site app-routes))
