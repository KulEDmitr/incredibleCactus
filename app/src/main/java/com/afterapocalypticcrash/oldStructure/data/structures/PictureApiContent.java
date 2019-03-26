package com.afterapocalypticcrash.oldStructure.data.structures;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import java.util.List;

public class PictureApiContent {

    private List<Results> results;

    public List<Results> getResults() {
        return results;
    }

    public void setResults(List<Results> results) {
        this.results = results;
    }

    public static class Results implements Parcelable {
        private String id;
        private int likes;
        private String description;
        private User user;
        private Urls urls;

        public Results(String id, int likes, String description, String username, String name,
                       String large, String http, String thumb, String regular) {
            this.id = id;
            this.likes = likes;
            this.description = description;
            this.user = new User(username, name, large, http);
            this.urls = new Urls(thumb, regular);
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public int getLikes() {
            return likes;
        }

        public void setLikes(int likes) {
            this.likes = likes;
        }

        public User getUser() {
            return user;
        }

        public void setUser(User user) {
            this.user = user;
        }

        public Urls getUrls() {
            return urls;
        }

        public void setUrls(Urls urls) {
            this.urls = urls;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        @NonNull
        @Override
        public String toString() {
            return (description == null)
                    ? ("This image has no description\nJust enjoy the picture")
                    : (description);
        }

        @NonNull
        public String getFullDescription() {
            String out;
            out = (description == null) ? ("") : (description + "\n");
            return out + "likes: " + Integer.toString(likes);
        }

        @NonNull
        public String getUserInfo() {
            return ("Photo by: " + getUser().getUsername()
                    + "\n( " + getUser().getName() + " )\nPress to go to Unsplash.com account");
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeStringArray(new String[]
                    {id, description, Integer.toString(likes), urls.getThumb(), urls.getRegular(),
                            user.getUsername(), user.getProfile_image().getLarge(),
                            user.getLinks().getHtml(), user.getName()});
        }

        protected Results(Parcel in) {
            String[] data = new String[9];
            in.readStringArray(data);
            id = data[0];
            description = data[1];
            likes = Integer.parseInt(data[2]);
            urls.setThumb(data[3]);
            urls.setRegular(data[4]);
            user.setUsername(data[5]);
            user.getProfile_image().setLarge(data[6]);
            user.getLinks().setHtml(data[7]);
            user.setName(data[8]);
        }

        public static final Creator<Results> CREATOR = new Creator<Results>() {
            @Override
            public Results createFromParcel(Parcel in) {
                return new Results(in);
            }

            @Override
            public Results[] newArray(int size) {
                return new Results[size];
            }
        };


        public static class User {

            private String username;
            private String name;
            private ProfileImage profile_image;
            private UserLinks links;

            public User(String username, String name, String large, String http) {
                this.username = username;
                this.name = name;
                this.profile_image = new ProfileImage(large);
                this.links = new UserLinks(http);
            }

            public String getUsername() {
                return username;
            }

            public void setUsername(String username) {
                this.username = username;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public ProfileImage getProfile_image() {
                return profile_image;
            }

            public void setProfile_image(ProfileImage profile_image) {
                this.profile_image = profile_image;
            }

            public UserLinks getLinks() {
                return links;
            }

            public void setLinks(UserLinks links) {
                this.links = links;
            }


            public static class ProfileImage {

                private String large;

                public ProfileImage(String large) {
                    this.large = large;
                }

                public String getLarge() {
                    return large;
                }

                public void setLarge(String large) {
                    this.large = large;
                }
            }

            public static class UserLinks {

                private String html;

                public UserLinks(String html) {
                    this.html = html;
                }

                public String getHtml() {
                    return html;
                }

                public void setHtml(String html) {
                    this.html = html;
                }
            }
        }

        public static class Urls {

            private String thumb;
            private String regular;

            public Urls(String thumb, String regular) {
                this.thumb = thumb;
                this.regular = regular;
            }

            public String getThumb() {
                return thumb;
            }

            public void setThumb(String thumb) {
                this.thumb = thumb;
            }

            public String getRegular() {
                return regular;
            }

            public void setRegular(String regular) {
                this.regular = regular;
            }
        }
    }
}
