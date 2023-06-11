from wordcloud import WordCloud
import matplotlib.pyplot as plt
from nltk.tokenize import word_tokenize

def generateWordCloud(text):
    words = word_tokenize(text)
    unique_words = set(words) 
    unique_text = ' '.join(unique_words)
    
    wordcloud = WordCloud(width=1600, height=800, max_font_size=200).generate(unique_text)
    plt.figure(figsize=(12,10))
    plt.imshow(wordcloud, interpolation="bilinear")
    plt.axis("off")
    plt.show()

def getData(file):
    with open(file, 'r', encoding='utf-8') as f:
        text = f.read()
    return text

def main():
    print("World Clouds preprocessed Russia:")
    generateWordCloud(getData('../dane/preprocessedRussia.txt'))
    print("World Clouds preprocessed Ukraine:")
    generateWordCloud(getData('../dane/preprocessedUkraine.txt'))
    print("World Clouds positive Russia:")
    generateWordCloud(getData('positiveTweetsRussia.txt'))
    print("World Clouds negative Russia:")
    generateWordCloud(getData('negativeTweetsRussia.txt'))
    print("World Clouds positive Ukraine:")
    generateWordCloud(getData('positiveTweetsUkraine.txt'))
    print("World Clouds negative Ukraine:")
    generateWordCloud(getData('negativeTweetsUkraine.txt'))
if __name__ == '__main__':
    main()