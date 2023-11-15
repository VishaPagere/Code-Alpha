//task 4

import twitter4j.*;
import twitter4j.auth.AccessToken;
import twitter4j.conf.ConfigurationBuilder;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;

import javax.swing.*;
import java.util.List;

public class SocialMediaAnalyticsTool {

    private static final String CONSUMER_KEY = "Your_Consumer_Key";
    private static final String CONSUMER_SECRET = "Your_Consumer_Secret";
    private static final String ACCESS_TOKEN = "Your_Access_Token";
    private static final String ACCESS_TOKEN_SECRET = "Your_Access_Token_Secret";

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                createAndShowGUI();
            } catch (TwitterException e) {
                e.printStackTrace();
            }
        });
    }

    private static void createAndShowGUI() throws TwitterException {
        JFrame frame = new JFrame("Social Media Analytics Tool");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        CategoryDataset dataset = createDataset();
        JFreeChart barChart = ChartFactory.createBarChart(
                "Sentiment Analysis",
                "Sentiment",
                "Count",
                dataset,
                PlotOrientation.VERTICAL,
                true, true, false);

        ChartPanel chartPanel = new ChartPanel(barChart);
        chartPanel.setPreferredSize(new java.awt.Dimension(560, 370));
        frame.setContentPane(chartPanel);

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private static CategoryDataset createDataset() throws TwitterException {
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();

        Twitter twitter = configureTwitter();
        Query query = new Query("Java Programming");
        query.setCount(100);
        QueryResult result = twitter.search(query);

        List<Status> tweets = result.getTweets();

        int positiveCount = 0;
        int neutralCount = 0;
        int negativeCount = 0;

        for (Status tweet : tweets) {
            String text = tweet.getText();
            String sentiment = performSentimentAnalysis(text);

            switch (sentiment) {
                case "Positive":
                    positiveCount++;
                    break;
                case "Neutral":
                    neutralCount++;
                    break;
                case "Negative":
                    negativeCount++;
                    break;
            }
        }

        dataset.addValue(positiveCount, "Sentiment", "Positive");
        dataset.addValue(neutralCount, "Sentiment", "Neutral");
        dataset.addValue(negativeCount, "Sentiment", "Negative");

        return dataset;
    }

    private static Twitter configureTwitter() {
        ConfigurationBuilder cb = new ConfigurationBuilder();
        cb.setDebugEnabled(true)
                .setOAuthConsumerKey(CONSUMER_KEY)
                .setOAuthConsumerSecret(CONSUMER_SECRET)
                .setOAuthAccessToken(ACCESS_TOKEN)
                .setOAuthAccessTokenSecret(ACCESS_TOKEN_SECRET);

        TwitterFactory tf = new TwitterFactory(cb.build());
        return tf.getInstance();
    }

    private static String performSentimentAnalysis(String text) {
        // Implement your sentiment analysis logic here
        // This is a placeholder; you may use NLP libraries or APIs for accurate sentiment analysis
        if (text.toLowerCase().contains("good")) {
            return "Positive";
        } else if (text.toLowerCase().contains("bad")) {
            return "Negative";
        } else {
            return "Neutral";
        }
    }
}
