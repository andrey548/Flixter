# Flixter
Flixter is an app that allows users to browse movies from the [The Movie Database API](http://docs.themoviedb.apiary.io/#).

## Flix Part 2

### User Stories

#### REQUIRED (10pts)

- [x] (8pts) Expose details of movie (ratings using RatingBar, popularity, and synopsis) in a separate activity.
- [x] (2pts) Allow video posts to be played in full-screen using the YouTubePlayerView.




## Flix Part 1

### User Stories


#### REQUIRED (10pts)
- [x] (10pts) User can view a list of movies (title, poster image, and overview) currently playing in theaters from the Movie Database API.

#### BONUS
- [ ] (2pts) Views should be responsive for both landscape/portrait mode.
   - [ ] (1pt) In portrait mode, the poster image, title, and movie overview is shown.
   - [ ] (1pt) In landscape mode, the rotated alternate layout should use the backdrop image instead and show the title and movie overview to the right of it.

- [ ] (2pts) Display a nice default [placeholder graphic](https://guides.codepath.org/android/Displaying-Images-with-the-Glide-Library#advanced-usage) for each image during loading
- [x] (2pts) Improved the user interface by experimenting with styling and coloring.
- [ ] (2pts) For popular movies (i.e. a movie voted for more than 5 stars), the full backdrop image is displayed. Otherwise, a poster image, the movie title, and overview is listed. Use Heterogenous RecyclerViews and use different ViewHolder layout files for popular movies and less popular ones.

### App Walkthough GIF


<img src="https://raw.githubusercontent.com/andrey548/Flixter/master/walkthrough2.gif" width=250><br>

### Notes
I reduced rating bar to 5 stars and devided the value that you get for rating by 2 in the DetailActivity where you set the value, becasuse 10 stars seemed like too many, and also made some additnoal style changes. In the deteailed view I'm showing the relase data insted of popularity, because I do not understand what popularity number would really mean when you show it. Unfortunelty I did not get to this week's additional stories, so I did not make and adjustments/aditions based on popularity value

Instead of displaying movie's title and movie's descriptiong I was displaying movie's description twice. 
I displayed the description in place where title should go. Fixed by first checking Movie model and noticing that getTitle()
was never used. When I went into MovieAdapter I noticed that I used getOverview() for both the title and the overview fields.
### Open-source libraries used

- [Android Async HTTP](https://github.com/codepath/CPAsyncHttpClient) - Simple asynchronous HTTP requests with JSON parsing
- [Glide](https://github.com/bumptech/glide) - Image loading and caching library for Androids
