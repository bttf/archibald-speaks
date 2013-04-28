(ns archibald-two.application
  (:use [hiccup.core]
        [hiccup.page])
  (:require [archibald-two.db]))

(def page-title
  "archibald speaks")

(defn content-box
  [content]
  [:div {:class "content-box"}
   content])

(defn main-template
  [body]
  (html5
    [:head
     [:title page-title]
     [:link {:rel "stylesheet" :href "/css/application.css"}]]
    [:body
     [:div {:class "container"}
      [:div {:class "heading"}
       [:h1 "archibald speaks"]]
      [:div {:class "nav-bar"}
       [:a {:href "/"} "home"]
       [:a {:href "/posts"} "posts"]
       [:a {:href "/about"} "about"]
       [:a {:href "/contact"} "contact"]]
      [:div {:class "body-div"}
       body]]]))

(defn index
  []
  (-> [:p "welcome to index"]
    (content-box)
    (main-template)))

(defn posts
  []
  (-> "Post after post after post, RIGHT HERE , NOT RIGHT NOW."
    (content-box)
    (main-template)))

(defn about
  []
  (-> "My name is Archibald, and I run the place"
    (content-box)
    (main-template)))

(defn contact 
  []
  (-> "Email me at archibald AT chewbonga DOT com"
    (content-box)
    (main-template)))

;; sql stuff
;;
;; (defn all-updates
;;   []
;;   (for [x (archibald-two.db/all-posts)]
;;     (-> [:span (:title x)]
;;       (content-box))))
