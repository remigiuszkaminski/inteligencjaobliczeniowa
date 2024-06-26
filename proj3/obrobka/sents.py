import ast
def categorize(data):
    positiveTweets = []
    negativeTweets = []

    for tweet, sentiment_scores, emotions in data:
        compound_score = sentiment_scores['compound']
        if compound_score > 0.1:
            positiveTweets.append(tweet)
        elif compound_score < -0.1:
            negativeTweets.append(tweet)
    
    return positiveTweets, negativeTweets


def saveBothToFiles(positiveTweets, negativeTweets, filename1, filename2):
    with open(filename1, "w", encoding="utf-8") as file:
        file.writelines("\n".join(positiveTweets))

    with open(filename2, "w", encoding="utf-8") as file:
        file.writelines("\n".join(negativeTweets))

def parse_sentiment_scores(line):
    sentiment_start = line.index("{'neg':")
    sentiment_end = line.index("},") + 1
    emotions_start = line.index("{", sentiment_end)
    emotions_end = line.index("};", emotions_start) + 1

    sentiment_scores = ast.literal_eval(line[sentiment_start:sentiment_end])
    emotions = ast.literal_eval(line[emotions_start:emotions_end])

    return sentiment_scores, emotions


def parse_data(file):
    data = []
    for line in file:
        line = line.strip()
        if line:
            tweet_end = line.index(",")
            tweet = line[:tweet_end]
            scores, emotions = parse_sentiment_scores(line)

            data.append((tweet, scores, emotions))

    return data



def main():
    with open("sentimentScoresRussia.txt", "r", encoding="utf-8") as file:
        dataRussia = parse_data(file)

    with open("sentimentScoresUkraine.txt", "r", encoding="utf-8") as file:
        dataUkraine = parse_data(file)

    positiveTweetsRussia, negativeTweetsRussia = categorize(dataRussia)
    saveBothToFiles(positiveTweetsRussia, negativeTweetsRussia, "positiveTweetsRussia.txt", "negativeTweetsRussia.txt")

    positiveTweetsUkraine, negativeTweetsUkraine = categorize(dataUkraine)
    saveBothToFiles(positiveTweetsUkraine, negativeTweetsUkraine, "positiveTweetsUkraine.txt", "negativeTweetsUkraine.txt")


if __name__ == '__main__':
    main()