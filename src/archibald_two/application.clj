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
       [:a {:href "/posts" :style "margin-right: 0px;"} "posts"]
       [:a {:href "/add-post" :style "margin-left: 0px;"} " [+]"]
       [:a {:href "/quotes" :style "margin-right: 0px;"} "quotes"]
       [:a {:href "/add-quote" :style "margin-left: 0px;"} " [+]"]
       [:a {:href "/about"} "about"]
       [:a {:href "/contact"} "contact"]]
      [:div {:class "body-div"}
       body]]]))

(def archibald-introduction
  (str "Hello human being, you have landed upon my web page."
       "I am ARCHIBALD, Lord and Master of the Divinity of the Seventh Cross."
       "My time here on Earth has allowed me to endow a lucky few to engage and"
       "indulge in the resources provided here. Please enjoy yourself, do not"
       "give into amazement, and save all praise for a time when you may reach me"
       "on a higher plane."
       "Enjoy,"
       "ARCHIBALD"))

(def main-page
  [:div {:class "main-page"}
   [:img {:src "/img/archibald.jpg"}]
   archibald-introduction])

(defn index
  []
  (-> main-page
    (content-box)
    (main-template)))

(defn posts
  []
  (-> (for [x (archibald-two.db/all-posts)]
        (-> [:div {:class "posts"}
             [:h2 {:class "title"} (:title x)]
             [:p {:class "content"} (:content x)]
             [:p {:class "tags"} (:tags x)]]
          (content-box)))
    (main-template)))

(def quote-form
  [:form {:method "post" :class "add-post"}
   "quote" 
   [:br]
   [:textarea {:name "quote"}]
   [:br]
   "author" 
   [:br]
   [:input {:type "text" :name "author"}]
   [:br]
   [:input {:type "submit" :value "submit"}]])

(defn quotes
  []
  (-> (for [x (archibald-two.db/all-quotes)]
        (-> [:p (str "\"" (:quote x) "\" - " (:author x))]
             (content-box)))
    (main-template)))

(defn add-quote
  []
  (-> quote-form
    (content-box)
    (main-template)))

(defn quote-added 
  [params]
  (archibald-two.db/add-quote-to-db (:quote params) (:author params))
  (-> (conj quote-form [:div {:class "post-added"} [:h2 (str (:author params) " quote added")]])
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

(def post-form
  [:form {:method "post" :class "add-post"}
   "title" 
   [:br]
   [:input {:type "text" :name "title"}]
   [:br]
   "content" 
   [:br]
   [:textarea {:name "content"}]
   [:br]
   "tags" 
   [:br]
   [:input {:type "text" :name "tags"}]
   [:br]
   [:input {:type "submit" :value "submit"}]])

(defn add-post
  []
  (-> post-form
    (content-box)
    (main-template)))

(defn post-added
  [params]
  (archibald-two.db/add-post-to-db (:title params) (:content params) (:tags params))
  (-> (conj post-form [:div {:class "post-added"} [:h2 (str "post \"" (:title params) "\" added")]])
    (content-box)
    (main-template)))


;; sql stuff
;;
;; (defn all-updates
;;   []
;;   (for [x (archibald-two.db/all-posts)]
;;     (-> [:span (:title x)]
;;       (content-box))))
