// See https://github.com/dialogflow/dialogflow-fulfillment-nodejs
// for Dialogflow fulfillment library docs, samples, and to report issues
'use strict';

const functions = require('firebase-functions');
const fetch = require('node-fetch');
const { WebhookClient } = require('dialogflow-fulfillment');
const {
	Card,
	Suggestion,
	Button
} = require('dialogflow-fulfillment');

process.env.DEBUG = 'dialogflow:debug'; // enables lib debugging statements

const DEAL_JSON_URL = "https://elijahverdoorn.com/chronogg/deal.json";

exports.dialogflowFirebaseFulfillment = functions.https.onRequest((request, response) => {
  const agent = new WebhookClient({ request, response });

	function fallback(agent) {
		let text = `Sorry, there's been an error. Please try again later.`
		agent.add(text)
	}

  async function getDailyDeal(agent) {
		let json =  await getDealJSON();
    let spokenText = `Today's deal is ${json.title}, it costs $${json.price}.`;
    let cardText = `${spokenText} That's a ${json.discountPercent}% discount; it costs $${json.steamPrice} on Steam otherwise.`;
    let sTitle = `$${json.price}`;
    let buttonText = `Open Chrono.gg`;

    agent.add(spokenText);
    agent.add(new Card({
      	title: json.title,
      	subtitle: sTitle,
        imageUrl: json.imgUrl,
        text: cardText,
        buttonText: buttonText,
        buttonUrl: json.link
		}));
  }

  let getDealJSON = () => {
		return fetch('https://elijahverdoorn.com/chronogg/deal.json').then(res => res.json());
  };

  // Run the proper function handler based on the matched Dialogflow intent name
  let intentMap = new Map();
  intentMap.set('GetDailyDeal', getDailyDeal);
	intentMap.set('', fallback);

  agent.handleRequest(intentMap);
});

